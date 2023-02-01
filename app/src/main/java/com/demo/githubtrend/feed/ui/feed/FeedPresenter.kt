package com.demo.githubtrend.feed.ui.feed

import com.demo.githubtrend.common.mvp.BasePresenter
import com.demo.githubtrend.feed.interactor.FeedInteractor
import com.demo.githubtrend.feed.model.Feed
import com.demo.githubtrend.feed.model.FeedContent
import com.demo.githubtrend.feed.model.QueryCondition
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

private const val ITEMS_PER_PAGE_DEFAULT = 30

class FeedPresenter(
    private val feedInteractor: FeedInteractor,
) : BasePresenter<FeedContract.View>(), FeedContract.Presenter {

    private var feeds by Delegates.observable(emptyList<Feed>()) { _, _, newValues ->
        view?.showFeed(newValues)
    }
    private val dateQuery = QueryCondition.CreatedUntil(30)
    private var maxItemCount = 1

    override fun loadFeed(isRefresh: Boolean) {
        if (feeds.size > maxItemCount && !isRefresh) return
        launch {
            try {
                view?.showLoading(true)

                val nextPage = (feeds.size / ITEMS_PER_PAGE_DEFAULT) + 1
                val content = feedInteractor.getFeeds(
                    date = dateQuery.query,
                    perPage = ITEMS_PER_PAGE_DEFAULT,
                    page = nextPage
                )
                view?.showLoading(false)
                handleResponse(isRefresh, content)
            } catch (e: Exception) {
                view?.showLoading(false)
                errorHandling(e)
            }
        }
    }

    private fun handleResponse(isRefresh: Boolean, response: FeedContent) {
        if (maxItemCount < response.totalCount) maxItemCount = response.totalCount
        feeds = if (isRefresh) {
            response.items
        } else {
            feeds + response.items
        }
    }
}
