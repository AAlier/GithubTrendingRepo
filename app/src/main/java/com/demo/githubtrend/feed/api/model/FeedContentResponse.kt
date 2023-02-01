package com.demo.githubtrend.feed.api.model

import com.google.gson.annotations.SerializedName

data class FeedContentResponse(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("incomplete_results") val incompleteResults: Boolean,
    @SerializedName("items") val items: List<FeedResponse>,
)
