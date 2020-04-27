package com.leo.android.project.ui.main

import androidx.multidex.MultiDex
import com.leo.android.project.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MainApplication : DaggerApplication(){

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent
            .factory()
            .create(this)

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
    }
}