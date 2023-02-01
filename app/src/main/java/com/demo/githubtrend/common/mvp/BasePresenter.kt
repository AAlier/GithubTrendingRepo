package com.demo.githubtrend.common.mvp

import androidx.annotation.CallSuper
import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<V : MvpView> : MvpPresenter<V>, CoroutineScope {

    @VisibleForTesting
    override var coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.Main.immediate

    protected var view: V? = null
        private set

    @CallSuper
    override fun attach(view: V) {
        this.view = view
    }

    @CallSuper
    override fun detach() {
        coroutineContext.cancelChildren()
        view = null
    }

    protected fun errorHandling(e: Exception?) {
        if (e == null) return
        // Implement if necessary
        when {
//            e.isServerError() -> view?.showServerError()
//            e.isNetworkError() -> view?.showNetworkError()
//            e.isRefreshTokenError() -> view?.showRefreshTokenError()
//            e.isServerTimeoutError() -> view?.showNetworkTimeoutError()
            else -> view?.showErrorMessage(e)
        }
    }
}
