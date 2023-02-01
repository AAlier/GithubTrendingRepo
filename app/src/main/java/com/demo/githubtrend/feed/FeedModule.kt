package com.demo.githubtrend.feed

import com.demo.githubtrend.common.di.InjectionModule
import com.demo.githubtrend.feed.api.FeedApi
import com.demo.githubtrend.feed.interactor.FeedInteractor
import com.demo.githubtrend.feed.repository.FeedRemoteRepository
import com.demo.githubtrend.feed.repository.FeedRemoteRepositoryImpl
import com.demo.githubtrend.feed.ui.feed.FeedContract
import com.demo.githubtrend.feed.ui.feed.FeedPresenter
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

object FeedModule : InjectionModule {
    override fun create(): Module = module {
        single { get<Retrofit>().create(FeedApi::class.java) } bind FeedApi::class
        singleOf(::FeedRemoteRepositoryImpl) bind FeedRemoteRepository::class
        singleOf(::FeedInteractor) bind FeedInteractor::class

        factoryOf(::FeedPresenter) bind FeedContract.Presenter::class
    }
}
