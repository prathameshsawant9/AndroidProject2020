package com.leo.android.project.di.component

import android.content.Context
import com.leo.android.project.di.module.RepoModule
import com.leo.android.project.ui.main.MainApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        RepoModule::class
    ]
)
interface AppComponent : AndroidInjector<MainApplication>{

    @Component.Factory
    interface AppComponentFactory{
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}