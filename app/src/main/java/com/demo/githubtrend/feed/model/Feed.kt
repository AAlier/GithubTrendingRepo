package com.demo.githubtrend.feed.model

import java.util.Calendar

data class Feed(
    val id: Long,
    val name: String,
    val fullName: String,
    val private: Boolean,
    val owner: User,
    val htmlUrl: String,
    val description: String,
    val fork: Boolean,
    val url: String,
    val createdAt: Calendar,
    val updatedAt: Calendar?,
    val pushedAt: Calendar?,
    val gitUrl: String,
    val sshUrl: String,
    val cloneUrl: String,
    val homepage: String,
    val size: Int,
    val stargazersCount: Int,
    val watchersCount: Int,
    val language: String,
    val forksCount: Int,
    val visibility: VisibilityType,
    val forks: Int,
    val watchers: Int,
)
