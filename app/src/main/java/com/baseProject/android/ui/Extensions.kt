package com.baseProject.android.ui

import android.os.Handler
import android.view.View

/**
 *
 * @author Abbas Asadi
 */
fun View.preventDoubleClick() {
    isClickable = false
    Handler().postDelayed({ isClickable = true }, 5000L)
}