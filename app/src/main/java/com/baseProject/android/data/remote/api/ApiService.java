package com.baseProject.android.data.remote.api;

import com.baseProject.android.data.remote.model.requestModel.login.LoginRequest;
import com.baseProject.android.data.remote.model.requestModel.logout.LogoutRequest;
import com.baseProject.android.data.remote.model.requestModel.signup.SignupRequest;
import com.baseProject.android.data.remote.model.responseModel.WrappedApiResponse;
import com.baseProject.android.data.remote.model.responseModel.chat.ChatListResponse;
import com.baseProject.android.data.remote.model.responseModel.login.AppInit;
import com.baseProject.android.data.remote.model.responseModel.login.LoginResponse;
import com.baseProject.android.data.remote.model.responseModel.usersForeign.UsersForeignResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * The interface api service.
 *
 * @author Abbas Asadi
 */
public interface ApiService {
    //-------------------------------------------- auth --------------------------------------------
    @GET("auth/init")
    Observable<WrappedApiResponse<AppInit>> initializationByToken();

    @POST("auth/login")
    Observable<WrappedApiResponse<LoginResponse>> login(@Body LoginRequest loginRequest);

    @POST("auth/signup")
    Observable<WrappedApiResponse<LoginResponse>> signup(@Body SignupRequest signupRequest);

    @POST("auth/logout")
    Observable<WrappedApiResponse<Boolean>> logout(@Body LogoutRequest logoutRequest);

    //-------------------------------------------- chat --------------------------------------------
    @GET("chat-channels")
    Observable<WrappedApiResponse<ChatListResponse>> channelList();

    @GET("users/foreign")
    Observable<WrappedApiResponse<UsersForeignResponse>> usersForeign(@Query("ids") String ids);

}
