package com.baseProject.android.ui.login.fragments.login

import androidx.lifecycle.MutableLiveData
import com.baseProject.android.data.DataWrapper
import com.baseProject.android.data.publicModel.exception.InternetConnectionException
import com.baseProject.android.data.remote.model.requestModel.login.LoginRequest
import com.baseProject.android.data.remote.model.responseModel.login.LoginResponse
import com.baseProject.android.data.repository.login.LoginRepository
import com.baseProject.android.ui.ParentViewModel
import com.baseProject.android.util.InternetUtil
import javax.inject.Inject

class LoginViewModel @Inject internal constructor(private val repository: LoginRepository) :
    ParentViewModel() {
    val response = MutableLiveData<DataWrapper<LoginResponse>>()

    fun loginRequest(body: LoginRequest) {
        if (InternetUtil.isInternetOn()) {
            loading.set(true)
            addToUnsubscribed(repository.login(body)
                .subscribe({ dataWrapper ->
                    loading.set(false)
                    response.postValue(dataWrapper as DataWrapper<LoginResponse>?)
                }) { throwable ->
                    loading.set(false)
                    exceptionParser(response, throwable)
                })
        } else {
            exceptionParser(response, InternetConnectionException())
        }
    }

}