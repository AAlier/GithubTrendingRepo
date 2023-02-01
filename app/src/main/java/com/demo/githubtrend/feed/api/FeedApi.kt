package com.demo.githubtrend.feed.api

import com.demo.githubtrend.feed.api.model.FeedContentResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FeedApi {
    @GET("search/repositories?q=created:%3E2021-01-01&sort=stars&order=desc")
    suspend fun loadFeed(
        @Query("q") date: String,
        @Query("sort") sortBy: String,
        @Query("order") orderBy: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
    ): FeedContentResponse
}
