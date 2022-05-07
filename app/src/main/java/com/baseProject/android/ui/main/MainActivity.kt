package com.baseProject.android.ui.main

import android.os.Bundle
import com.baseProject.android.R
import com.baseProject.android.util.locale.LocaleAwareCompatActivity

class MainActivity : LocaleAwareCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}