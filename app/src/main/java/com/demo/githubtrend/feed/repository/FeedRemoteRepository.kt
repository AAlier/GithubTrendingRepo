package com.demo.githubtrend.feed.repository

import com.demo.githubtrend.feed.model.FeedContent
import com.demo.githubtrend.feed.model.OrderBy
import com.demo.githubtrend.feed.model.SortBy

interface FeedRemoteRepository {
    suspend fun getFeeds(date: String, orderBy: OrderBy, sortBy: SortBy, perPage: Int, page: Int): FeedContent
}
