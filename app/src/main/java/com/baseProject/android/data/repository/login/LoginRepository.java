package com.baseProject.android.data.repository.login;

import com.baseProject.android.data.DataWrapper;
import com.baseProject.android.data.publicModel.exception.TokenNotVerifiedException;
import com.baseProject.android.data.remote.api.ApiService;
import com.baseProject.android.data.remote.model.requestModel.login.LoginRequest;
import com.baseProject.android.data.remote.model.requestModel.logout.LogoutRequest;
import com.baseProject.android.data.remote.model.requestModel.signup.SignupRequest;
import com.baseProject.android.data.remote.util.HttpStatusCode;
import com.baseProject.android.util.Constants;
import com.baseProject.android.util.MessageMap;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class LoginRepository {
    private final ApiService mApiService;

    @Inject
    public LoginRepository(ApiService apiService) {
        mApiService = apiService;
    }

    public Observable<DataWrapper<?>> initializationByToken() {
        return mApiService.initializationByToken()
                .subscribeOn(Schedulers.io())
                .doOnError(Throwable::printStackTrace)
                .observeOn(Schedulers.io())
                .flatMap(response -> {
                    if (HttpStatusCode.isHttpSuccessWithData(response)) {
                        return Observable.just(DataWrapper.success(response.data));
                    } else {
                        return Observable.just(DataWrapper.serverError(response.errorCodes()));
                    }
                }).onErrorResumeNext(t -> {
                    if (HttpStatusCode.isHttp401Error(t) /*|| HttpStatusCode.isHttp407Error(t)*/) {
                        return Observable.just(DataWrapper.error(null, new TokenNotVerifiedException(),
                                MessageMap.getMessageWithThrowableException(Constants.NETWORK_EXCEPTION_CODE.FAILED_TO_CONNECT)));
                    }
                    return Observable.just(DataWrapper.error(null, t, ""));
                });
    }

    public Observable<DataWrapper<?>> login(LoginRequest body) {
        return mApiService.login(body)
                .subscribeOn(Schedulers.io())
                .doOnError(Throwable::printStackTrace)
                .observeOn(Schedulers.io())
                .flatMap(response -> {
                    if (HttpStatusCode.isHttpSuccessWithData(response)) {
                        return Observable.just(DataWrapper.success(response.data));
                    } else {
                        return Observable.just(DataWrapper.serverError(response.errorCodes()));
                    }
                }).onErrorResumeNext(t -> {
                    if (HttpStatusCode.isHttp401Error(t) /*|| HttpStatusCode.isHttp407Error(t)*/) {
                        return Observable.just(DataWrapper.error(null, new TokenNotVerifiedException(),
                                MessageMap.getMessageWithThrowableException(Constants.NETWORK_EXCEPTION_CODE.FAILED_TO_CONNECT)));
                    }
                    return Observable.just(DataWrapper.error(null, t, ""));
                });
    }

    public Observable<DataWrapper<?>> signup(SignupRequest body) {
        return mApiService.signup(body)
                .subscribeOn(Schedulers.io())
                .doOnError(Throwable::printStackTrace)
                .observeOn(Schedulers.io())
                .flatMap(response -> {
                    if (HttpStatusCode.isHttpSuccessWithData(response)) {
                        return Observable.just(DataWrapper.success(response.data));
                    } else {
                        return Observable.just(DataWrapper.serverError(response.errorCodes()));
                    }
                }).onErrorResumeNext(t -> {
                    if (HttpStatusCode.isHttp401Error(t) /*|| HttpStatusCode.isHttp407Error(t)*/) {
                        return Observable.just(DataWrapper.error(null, new TokenNotVerifiedException(),
                                MessageMap.getMessageWithThrowableException(Constants.NETWORK_EXCEPTION_CODE.FAILED_TO_CONNECT)));
                    }
                    return Observable.just(DataWrapper.error(null, t, ""));
                });
    }

    public Observable<DataWrapper<?>> logout(LogoutRequest body) {
        return mApiService.logout(body)
                .subscribeOn(Schedulers.io())
                .doOnError(Throwable::printStackTrace)
                .observeOn(Schedulers.io())
                .flatMap(response -> {
                    if (HttpStatusCode.isHttpSuccessWithData(response)) {
                        return Observable.just(DataWrapper.success(true));
                    } else {
                        return Observable.just(DataWrapper.serverError(response.errorCodes()));
                    }
                }).onErrorResumeNext(t -> {
                    if (HttpStatusCode.isHttp401Error(t) /*|| HttpStatusCode.isHttp407Error(t)*/) {
                        return Observable.just(DataWrapper.error(null, new TokenNotVerifiedException(),
                                MessageMap.getMessageWithThrowableException(Constants.NETWORK_EXCEPTION_CODE.FAILED_TO_CONNECT)));
                    }
                    return Observable.just(DataWrapper.error(null, t, ""));
                });
    }

}
