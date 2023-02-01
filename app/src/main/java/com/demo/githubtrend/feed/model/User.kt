package com.demo.githubtrend.feed.model

data class User(
    val id: Long,
    val login: String,
    val avatarUrl: String,
    val url: String,
    val htmlUrl: String,
    val followersUrl: String,
    val followingUrl: String,
    val gistsUrl: String,
    val starredUrl: String,
    val subscriptionsUrl: String,
    val organizationsUrl: String,
    val reposUrl: String,
    val type: String,
    val siteAdmin: Boolean,
)
