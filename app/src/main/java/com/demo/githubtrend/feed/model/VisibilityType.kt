package com.demo.githubtrend.feed.model

import com.google.gson.annotations.SerializedName

enum class VisibilityType {
    @SerializedName("public") PUBLIC,
    @SerializedName("private") PRIVATE,
}
