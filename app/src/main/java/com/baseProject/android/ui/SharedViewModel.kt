package com.baseProject.android.ui

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class SharedViewModel @Inject internal constructor() : ParentViewModel() {
    val isFirstPageCompleted = MutableLiveData<Boolean>(false)
    val name = MutableLiveData<String>()
    val phone = MutableLiveData<String>()
    val address = MutableLiveData<String>()
    val province = MutableLiveData<String>()
    val city = MutableLiveData<String>()
}