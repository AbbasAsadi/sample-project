package com.baseProject.android.data.remote.interceptor;

import static com.baseProject.android.data.remote.util.HttpStatusCode.BAD_TOKEN;
import static com.baseProject.android.data.remote.util.HttpStatusCode.TOKEN_NOT_ALLOWED;

import android.app.Application;
import android.text.TextUtils;

import com.baseProject.android.data.remote.model.responseModel.WrappedApiResponse;
import com.baseProject.android.data.remote.util.HttpStatusCode;
import com.baseProject.android.util.Constants;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


/**
 * The type Api response interceptor.
 * this interceptor control response and wrap api response
 *
 * @author Abbas Asadi
 */
public class ApiResponseInterceptor implements Interceptor {

    private static final MediaType JSON = MediaType.parse(Constants.MEDIA_TYPE.JSON);
    private static final String TAG = "ApiResponseInterceptor";
    private final Application application;

    /**
     * Instantiates a new Api response interceptor.
     *
     * @param application the application
     */
    public ApiResponseInterceptor(Application application) {
        this.application = application;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        JSONObject wrappedResponseJson = new JSONObject();
        Request request = chain.request();
        Response response = chain.proceed(request);
        final ResponseBody body = response.body();
        String bodyString = body.string();

        try {
            wrappedResponseJson.put(WrappedApiResponse.WrappedApiResponseJSONKey.STATUS, response.code());
            if (response.code() >= 200 && response.code() < 300) {
                if (!TextUtils.isEmpty(bodyString)) {
                    generateWrappedResponseJson(wrappedResponseJson, bodyString);
                }
            } else if (HttpStatusCode.isHttp403Error(response)) {
                if (application != null) {
                    return response.newBuilder().code(TOKEN_NOT_ALLOWED).body(ResponseBody.create(bodyString, JSON)).build();
                } else {
                    return response;
                }
            } else if (HttpStatusCode.isHttp401Error(response)) {
//                userNotAuthenticated();
                return response.newBuilder().code(BAD_TOKEN).body(ResponseBody.create(bodyString, JSON)).build();
            } else if (response.code() >= 400) {
                JSONArray jsonArray = new JSONArray(bodyString);
                wrappedResponseJson.put(WrappedApiResponse.WrappedApiResponseJSONKey.SERVER_ERROR, jsonArray);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final Response.Builder newResponse = response.newBuilder()
                .code(200)
                .body(ResponseBody.create(wrappedResponseJson.toString(), JSON));
        return newResponse.build();
    }


    private void userNotAuthenticated() {

    }


    private void generateWrappedResponseJson(JSONObject wrappedResponseJson, String bodyString) throws JSONException {
        Object json = new JSONTokener(bodyString).nextValue();
        if (json instanceof JSONObject) {
            JSONObject jsonObject = new JSONObject(bodyString);
            wrappedResponseJson.put(WrappedApiResponse.WrappedApiResponseJSONKey.DATA, jsonObject);
        } else if (json instanceof JSONArray) {
            JSONArray jsonArray = new JSONArray(bodyString);
            wrappedResponseJson.put(WrappedApiResponse.WrappedApiResponseJSONKey.DATA, jsonArray);
        } else {
            Map<String, String> value = new HashMap<>();
            value.put("value", bodyString);
            String valueJson = new Gson().toJson(value);
            JSONObject jsonObject = new JSONObject(valueJson);
            wrappedResponseJson.put(WrappedApiResponse.WrappedApiResponseJSONKey.DATA, jsonObject);
        }
    }
}
