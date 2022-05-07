package com.baseProject.android.data.remote.api;

import com.baseProject.android.data.remote.model.requestModel.login.LoginRequest;
import com.baseProject.android.data.remote.model.requestModel.logout.LogoutRequest;
import com.baseProject.android.data.remote.model.requestModel.signup.SignupRequest;
import com.baseProject.android.data.remote.model.responseModel.WrappedApiResponse;
import com.baseProject.android.data.remote.model.responseModel.login.AppInit;
import com.baseProject.android.data.remote.model.responseModel.login.LoginResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

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
    Observable<WrappedApiResponse<LoginResponse>> channelList();

}
