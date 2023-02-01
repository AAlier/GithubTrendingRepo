package com.demo.githubtrend.common.storage

import android.content.Context
import com.demo.githubtrend.common.di.InjectionModule
import org.koin.core.module.Module
import org.koin.dsl.module

object StorageModule : InjectionModule {
    override fun create(): Module = module {
        single {
            get<Context>().getSharedPreferences("demo", Context.MODE_PRIVATE)
        }
    }
}
