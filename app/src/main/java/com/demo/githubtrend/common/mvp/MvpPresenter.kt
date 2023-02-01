package com.demo.githubtrend.common.mvp

interface MvpPresenter<V : MvpView> {

    fun attach(view: V)
    fun detach()
}
