package com.leo.android.project.di.component

import android.content.Context
import com.leo.android.project.di.module.EspressoModule
import com.leo.android.project.di.module.FragmentModule
import com.leo.android.project.di.module.RepositoryModule
import com.leo.android.project.di.module.VMBuilderModule
import com.leo.android.project.ui.main.MainApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        RepositoryModule::class,
        VMBuilderModule::class,
        EspressoModule::class,
        FragmentModule::class
    ]
)
interface AppComponent : AndroidInjector<MainApplication>{

    @Component.Factory
    interface AppComponentFactory{
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}