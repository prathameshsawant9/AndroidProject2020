package com.leo.android.project.di.component

import android.content.Context
import com.leo.android.project.data.repo.MainRepository
import com.leo.android.project.data.repo.remote.RemoteRepository
import com.leo.android.project.di.module.*
import com.leo.android.project.di.module.TestEspressoModule
import com.leo.android.project.test.idling.EspressoIdlingResource
import com.leo.android.project.ui.main.MainApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        TestRepositoryModule::class,
        VMBuilderModule::class,
        TestEspressoModule::class,
        FragmentModule::class
    ]
)
interface TestAppComponent : AndroidInjector<MainApplication> {

    @Component.Factory
    interface TestAppComponentFactory{
        fun create(@BindsInstance applicationContext: Context,
                   @BindsInstance remote: RemoteRepository): TestAppComponent
    }

    /*
     * Exposing the repository
     */
    var mainRepository: MainRepository
}