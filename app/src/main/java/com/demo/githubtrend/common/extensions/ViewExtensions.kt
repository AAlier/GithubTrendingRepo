package com.demo.githubtrend.common.extensions

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.doOnDetach

fun Activity.hideKeyboard() {
    currentFocus?.hideKeyboard()
}

fun View.hideKeyboard() {
    clearFocus()
    val runnable = Runnable {
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(windowToken, 0)
    }
    post(runnable)
    doOnDetach { removeCallbacks(runnable) }
}

fun ViewGroup.inflater(): LayoutInflater {
    return LayoutInflater.from(context)
}
