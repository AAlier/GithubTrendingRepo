package com.demo.githubtrend.feed.interactor

import com.demo.githubtrend.feed.model.FeedContent
import com.demo.githubtrend.feed.model.OrderBy
import com.demo.githubtrend.feed.model.SortBy
import com.demo.githubtrend.feed.repository.FeedRemoteRepository

class FeedInteractor(
    private val feedRemoteRepository: FeedRemoteRepository
) {
    suspend fun getFeeds(
        date: String,
        orderBy: OrderBy = OrderBy.DESC,
        sortBy: SortBy = SortBy.STARS,
        perPage: Int,
        page: Int
    ): FeedContent {
        return feedRemoteRepository.getFeeds(date, orderBy, sortBy, perPage, page)
    }
}
