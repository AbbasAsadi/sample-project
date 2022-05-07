package com.baseProject.android.data.remote.interceptor;


import static com.baseProject.android.data.remote.RemoteConstants.AUTHORIZATION;

import com.baseProject.android.data.remote.authenticator.TokenManager;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * @author Abbas Asadi
 */
public class RequestInterceptor implements Interceptor {

    /**
     * @param chain
     * @return Response
     * @throws IOException add request headers to all request
     */
    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("User-Agent", CallApiHelper.createUserAgent());
        builder.addHeader("Accept-Encoding", "identity");
        if (TokenManager.getToken() != null) {
            builder.addHeader(AUTHORIZATION, TokenManager.getToken());
        }
        return chain.proceed(builder.build());
    }
}
