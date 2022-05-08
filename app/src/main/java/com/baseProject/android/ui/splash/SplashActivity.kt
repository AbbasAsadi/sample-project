package com.baseProject.android.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.baseProject.android.R
import com.baseProject.android.data.remote.authenticator.TokenManager
import com.baseProject.android.ui.login.activity.LoginActivity
import com.baseProject.android.ui.main.MainActivity
import com.baseProject.android.util.locale.LocaleAwareCompatActivity


class SplashActivity : LocaleAwareCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed(
            {
                if (TokenManager.hasToken()) startActivity(
                    Intent(
                        this@SplashActivity,
                        MainActivity::class.java
                    )
                ) else startActivity(
                    Intent(
                        this@SplashActivity,
                        LoginActivity::class.java
                    )
                )
            },
            2000
        )
    }
}