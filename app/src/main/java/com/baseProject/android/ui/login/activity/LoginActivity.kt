package com.baseProject.android.ui.login.activity

import android.os.Bundle
import com.baseProject.android.R
import com.baseProject.android.util.locale.LocaleAwareCompatActivity

class LoginActivity : LocaleAwareCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}