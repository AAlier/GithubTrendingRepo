package com.demo.githubtrend.common.mvp

import androidx.annotation.StringRes

interface MvpView {
    fun showServerError()
    fun showNetworkError()
    fun showRefreshTokenError()
    fun showNetworkTimeoutError()
    fun showErrorMessage(e: Throwable? = null)
    fun showErrorMessage(@StringRes messageRes: Int)
}
