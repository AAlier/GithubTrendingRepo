package com.demo.githubtrend.common.network

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.RawRes
import androidx.core.content.edit

private const val ADDRESS_KEY = "ADDRESS_KEY"
private const val PORT_KEY = "PORT_KEY"
private const val API_VERSION = "API_VERSION"
private const val SSL_ENABLED_KEY = "SSL_ENABLED_KEY"
private const val CERT_RES_KEY = "CERT_RES_KEY"

class EnvironmentManager(
    private val sharedPreferences: SharedPreferences,
    private val defaultAddress: String,
    private val defaultPort: Int,
    private val defaultApiVersion: Int,
    private val defaultIsSslEnabled: Boolean,
    private val defaultCertResName: String
) {

    fun loadEnvironment(context: Context) = with(sharedPreferences) {
        Environment(
            baseAddress = getString(ADDRESS_KEY, defaultAddress)!!,
            isSslEnabled = getBoolean(SSL_ENABLED_KEY, defaultIsSslEnabled),
            certRes = getCertResIdByName(context, defaultCertResName),
            apiVersion = getInt(API_VERSION, defaultApiVersion),
            port = getInt(PORT_KEY, defaultPort),
        )
    }

    fun saveEnvironment(context: Context, environment: Environment) {
        sharedPreferences.edit {
            putBoolean(SSL_ENABLED_KEY, environment.isSslEnabled)
            putString(ADDRESS_KEY, environment.baseAddress)
            putString(CERT_RES_KEY, context.resources.getResourceName(environment.certRes))
            putInt(API_VERSION, environment.apiVersion)
            putInt(PORT_KEY, environment.port)
        }
    }

    @RawRes
    fun getCertResIdByName(context: Context, name: String): Int {
        val (resType, resName) = name
            .split(':')
            .last()
            .split('/')

        return context.resources.getIdentifier(resName, resType, context.packageName)
    }
}
