package com.baseProject.android.ui.login.fragments.signup

import androidx.lifecycle.MutableLiveData
import com.baseProject.android.data.DataWrapper
import com.baseProject.android.data.publicModel.exception.InternetConnectionException
import com.baseProject.android.data.remote.authenticator.TokenManager
import com.baseProject.android.data.remote.model.requestModel.signup.SignupRequest
import com.baseProject.android.data.remote.model.responseModel.login.LoginResponse
import com.baseProject.android.data.repository.login.LoginRepository
import com.baseProject.android.ui.ParentViewModel
import com.baseProject.android.util.InternetUtil
import com.baseProject.android.util.PrefManager
import javax.inject.Inject

class SignupViewModel @Inject internal constructor(private val repository: LoginRepository) :
    ParentViewModel() {
    val response = MutableLiveData<DataWrapper<LoginResponse>>()

    fun signup(body: SignupRequest) {
        if (InternetUtil.isInternetOn()) {
            loading.set(true)
            addToUnsubscribed(repository.signup(body)
                .subscribe({ dataWrapper ->
                    loading.set(false)
                    val data = dataWrapper as DataWrapper<LoginResponse>
                    TokenManager.saveAccessToken(data.data!!.token)
                    PrefManager.setUserID(data.data.appInit?.user?.id!!)
                    response.postValue(data)
                }) { throwable ->
                    loading.set(false)
                    exceptionParser(response, throwable)
                })
        } else {
            exceptionParser(response, InternetConnectionException())
        }
    }
}