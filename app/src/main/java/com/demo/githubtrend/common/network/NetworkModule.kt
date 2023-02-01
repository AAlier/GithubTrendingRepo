package com.demo.githubtrend.common.network

import android.content.Context
import com.demo.githubtrend.R
import com.demo.githubtrend.common.di.InjectionModule
import com.demo.githubtrend.common.network.NetworkModule.DEFAULT_CONNECT_TIMEOUT_SECONDS
import com.demo.githubtrend.common.network.NetworkModule.DEFAULT_READ_TIMEOUT_SECONDS
import com.demo.githubtrend.common.network.adapter.DateTimeTypeAdapter
import com.google.gson.GsonBuilder
import org.koin.android.BuildConfig
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Calendar

/**
 * Network Configuration - отвечает за :
 * 1. [DEFAULT_CONNECT_TIMEOUT_SECONDS] / [DEFAULT_READ_TIMEOUT_SECONDS]
 * 2. Interceptor:
 *          a. Server Error Handling
 *          b. Token Interceptor -> Refresh token
 *          c. Server Time Interceptor -> Fetch Time with Server
 * 3. HttpLoggingInterceptor - Логирование запросов в логах
 * */
object NetworkModule : InjectionModule {

    private const val DEFAULT_CONNECT_TIMEOUT_SECONDS = 90L
    private const val DEFAULT_READ_TIMEOUT_SECONDS = 90L

    override fun create() = module {
        single {
            with(get<Context>()) {
                EnvironmentManager(
                    sharedPreferences = get(),
                    defaultAddress = getString(R.string.serverHost),
                    defaultPort = resources.getInteger(R.integer.serverPort),
                    defaultApiVersion = 1,
                    defaultIsSslEnabled = resources.getBoolean(R.bool.enableSsl),
                    defaultCertResName = getString(R.string.certResName),
                )
            }
        }

        single { get<EnvironmentManager>().loadEnvironment(get()) } bind Environment::class

        single {
            GsonBuilder()
                .apply { if (BuildConfig.DEBUG) setPrettyPrinting() }
                .registerTypeAdapter(Calendar::class.java, DateTimeTypeAdapter)
                .create()
        }

        single {
            Retrofit.Builder()
                .baseUrl(get<Environment>().restAddress)
                .addConverterFactory(GsonConverterFactory.create(get()))
                .build()
        }
    }
}
