package com.demo.githubtrend.common.mvp

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseMvpFragment<V : MvpView, P : MvpPresenter<V>>(
    @LayoutRes layoutRes: Int
) : Fragment(layoutRes), MvpView {

    abstract val presenter: P

    @CallSuper
    @Suppress("UNCHECKED_CAST")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this as V)
    }

    override fun showErrorMessage(e: Throwable?) {
    }

    override fun showErrorMessage(messageRes: Int) {
    }

    override fun showNetworkError() {
    }

    override fun showNetworkTimeoutError() {
    }

    override fun showRefreshTokenError() {
    }

    override fun showServerError() {
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detach()
    }
}
