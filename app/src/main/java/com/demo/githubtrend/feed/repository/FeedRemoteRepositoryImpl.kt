package com.demo.githubtrend.feed.repository

import com.demo.githubtrend.feed.api.FeedApi
import com.demo.githubtrend.feed.api.FeedMapper
import com.demo.githubtrend.feed.model.FeedContent
import com.demo.githubtrend.feed.model.OrderBy
import com.demo.githubtrend.feed.model.SortBy

class FeedRemoteRepositoryImpl(
    private val feedApi: FeedApi,
) : FeedRemoteRepository {

    override suspend fun getFeeds(
        date: String,
        orderBy: OrderBy,
        sortBy: SortBy,
        perPage: Int,
        page: Int
    ): FeedContent {
        val response = feedApi.loadFeed(
            date = date,
            sortBy = sortBy.value,
            orderBy = orderBy.value,
            perPage = perPage,
            page = page,
        )
        return FeedMapper.fromNetwork(response)
    }
}
