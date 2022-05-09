package com.baseProject.android.ui.chat

import androidx.lifecycle.MutableLiveData
import com.baseProject.android.data.DataWrapper
import com.baseProject.android.data.publicModel.exception.InternetConnectionException
import com.baseProject.android.data.remote.authenticator.TokenManager
import com.baseProject.android.data.remote.model.requestModel.logout.LogoutRequest
import com.baseProject.android.data.remote.model.responseModel.chat.ChatListResponse
import com.baseProject.android.data.remote.model.responseModel.usersForeign.UsersForeignResponse
import com.baseProject.android.data.repository.chat.ChatRepository
import com.baseProject.android.ui.ParentViewModel
import com.baseProject.android.util.InternetUtil
import javax.inject.Inject

class ChatViewModel @Inject internal constructor(private val repository: ChatRepository) :
    ParentViewModel() {
    val response = MutableLiveData<DataWrapper<ChatListResponse>>()
    val usersResponse = MutableLiveData<DataWrapper<UsersForeignResponse>>()

    fun getChatList() {
        if (InternetUtil.isInternetOn()) {
            loading.set(true)
            addToUnsubscribed(repository.channelList()
                .subscribe({ dataWrapper ->
                    loading.set(false)
                    response.postValue(dataWrapper as DataWrapper<ChatListResponse>?)
                }) { throwable ->
                    loading.set(false)
                    exceptionParser(response, throwable)
                })
        } else {
            exceptionParser(response, InternetConnectionException())
        }
    }

    fun usersForeign(ids: String) {
        if (InternetUtil.isInternetOn()) {
            loading.set(true)
            addToUnsubscribed(repository.usersForeign(ids)
                .subscribe({ dataWrapper ->
                    loading.set(false)
                    usersResponse.postValue(dataWrapper as DataWrapper<UsersForeignResponse>?)

                }) { throwable ->
                    loading.set(false)
                    exceptionParser(usersResponse, throwable)
                })
        } else {
            exceptionParser(usersResponse, InternetConnectionException())
        }
    }

    fun signOut() {
        if (InternetUtil.isInternetOn()) {
            loading.set(true)
            addToUnsubscribed(repository.logout(LogoutRequest(true))
                .subscribe({
                    loading.set(false)
                    TokenManager.clearToken()
                }) {
                    loading.set(false)
                })
        }
    }
}