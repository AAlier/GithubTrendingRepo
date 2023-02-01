package com.demo.githubtrend.feed.ui.feed

import com.demo.githubtrend.common.mvp.MvpPresenter
import com.demo.githubtrend.common.mvp.MvpView
import com.demo.githubtrend.feed.model.Feed

interface FeedContract {
    interface View : MvpView {
        fun showFeed(items: List<Feed>)
        fun showLoading(isLoading: Boolean)
    }

    interface Presenter : MvpPresenter<View> {
        fun loadFeed(isRefresh: Boolean = false)
    }
}
