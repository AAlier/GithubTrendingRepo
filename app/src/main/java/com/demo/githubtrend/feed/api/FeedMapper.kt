package com.demo.githubtrend.feed.api

import com.demo.githubtrend.feed.api.model.FeedContentResponse
import com.demo.githubtrend.feed.api.model.FeedResponse
import com.demo.githubtrend.feed.model.Feed
import com.demo.githubtrend.feed.model.FeedContent

object FeedMapper {
    fun fromNetwork(value: FeedContentResponse): FeedContent {
        return FeedContent(
            totalCount = value.totalCount,
            items = fromNetwork(value.items)
        )
    }

    fun fromNetwork(values: List<FeedResponse>): List<Feed> {
        return values.map(::fromNetwork)
    }

    fun fromNetwork(value: FeedResponse): Feed {
        return Feed(
            id = value.id,
            name = value.name,
            fullName = value.fullName.orEmpty(),
            private = value.private,
            owner = UserMapper.fromNetwork(value.owner),
            htmlUrl = value.htmlUrl,
            description = value.description.orEmpty(),
            fork = value.fork,
            url = value.url,
            createdAt = value.createdAt,
            updatedAt = value.updatedAt,
            pushedAt = value.pushedAt,
            gitUrl = value.gitUrl.orEmpty(),
            sshUrl = value.sshUrl.orEmpty(),
            cloneUrl = value.cloneUrl.orEmpty(),
            homepage = value.homepage.orEmpty(),
            size = value.size ?: 0,
            stargazersCount = value.stargazersCount ?: 0,
            watchersCount = value.watchersCount ?: 0,
            language = value.language.orEmpty(),
            forksCount = value.forksCount ?: 0,
            visibility = value.visibility,
            forks = value.forks ?: 0,
            watchers = value.watchers ?: 0,
        )
    }
}
