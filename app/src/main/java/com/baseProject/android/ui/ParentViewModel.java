package com.baseProject.android.ui;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.baseProject.android.data.DataWrapper;
import com.baseProject.android.data.publicModel.exception.InternalServerException;
import com.baseProject.android.data.publicModel.exception.InternetConnectionException;
import com.baseProject.android.data.publicModel.exception.TokenNotVerifiedException;
import com.baseProject.android.data.publicModel.exception.UnknownException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * @author Abbas Asadi
 */
public class ParentViewModel extends ViewModel {

    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    public ObservableBoolean loading = new ObservableBoolean(false);
    public ObservableBoolean empty = new ObservableBoolean(false);

    @Deprecated
    protected CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    protected void addToUnsubscribed(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    /**
     * Convert server errors to exception
     * the exception parser send exceptions to fabric dashboard
     *
     * @param data      , fill data with correct exception and
     *                  post data
     * @param throwable , get http exception and choose correct exception to show user
     */

    protected void exceptionParser(@NonNull MutableLiveData data, @NonNull Throwable throwable) {

        throwable.printStackTrace();
      /*  if (throwable instanceof SSLException) {
            data.postValue(DataWrapper.error(null, new TokenNotVerifiedException(), null));
        } else*/
        if (throwable instanceof InternetConnectionException) {
            //data.postValue(DataWrapper.error(null, throwable, MessageMap.getMessageWithThrowableException(Constants.NETWORK_EXCEPTION_CODE.NO_INTERNET_CONNECTION)));
            data.postValue(DataWrapper.connectionError());
        } else if (throwable instanceof SocketTimeoutException) {
            //data.postValue(DataWrapper.error(null, new CustomSocketTimeoutException(), MessageMap.getMessageWithThrowableException(Constants.NETWORK_EXCEPTION_CODE.SOCKET_TIME_OUT)));
            data.postValue(DataWrapper.connectionError());
        } else if (throwable instanceof IllegalStateException) {
            data.postValue(DataWrapper.error(null, new IllegalStateException(), null));
        } else if (throwable instanceof TokenNotVerifiedException) {
            data.postValue(DataWrapper.error(null, new TokenNotVerifiedException(), null));
        } else if (throwable instanceof ConnectException) {
            data.postValue(DataWrapper.connectionError());
        } else if (throwable instanceof HttpException) {
            retrofitHttpException(data, throwable);
        } else {
            data.postValue(DataWrapper.error(null, new UnknownException(throwable), null));
        }
        //TODO: add json syntax exception

    }


    private void retrofitHttpException(@NonNull MutableLiveData data, @NonNull Throwable throwable) {
        HttpException exception = (HttpException) throwable;

        switch (exception.code()) {
            case 401:
            case 403:
            case 417:
            case 407:
                data.postValue(DataWrapper.error(null, new TokenNotVerifiedException(), null));
                break;

            case 500:
                data.postValue(DataWrapper.error(null, new InternalServerException(), null));
                break;
            default:
                data.postValue(DataWrapper.error(null, throwable, null));
                break;
        }
    }
}
