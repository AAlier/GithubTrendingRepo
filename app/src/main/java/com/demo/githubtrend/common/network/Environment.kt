package com.demo.githubtrend.common.network

import androidx.annotation.RawRes

data class Environment(
    val baseAddress: String,
    val port: Int = 0,
    val isSslEnabled: Boolean,
    val apiVersion: Int,
    @RawRes val certRes: Int
) {
    val restAddress: String =
        "${if (isSslEnabled) "https" else "http"}://$baseAddress${if (port != 0) ":$port" else ""}/"
}
