package com.demo.githubtrend.feed.api

import com.demo.githubtrend.feed.api.model.UserResponse
import com.demo.githubtrend.feed.model.User

object UserMapper {
    fun fromNetwork(userResponse: UserResponse): User {
        return User(
            id = userResponse.id,
            login = userResponse.login,
            avatarUrl = userResponse.avatarUrl,
            url = userResponse.url,
            htmlUrl = userResponse.htmlUrl,
            followersUrl = userResponse.followersUrl,
            followingUrl = userResponse.followingUrl,
            gistsUrl = userResponse.gistsUrl,
            starredUrl = userResponse.starredUrl,
            subscriptionsUrl = userResponse.subscriptionsUrl,
            organizationsUrl = userResponse.organizationsUrl,
            reposUrl = userResponse.reposUrl,
            type = userResponse.type,
            siteAdmin = userResponse.siteAdmin,
        )
    }
}
