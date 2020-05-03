package com.leo.android.project.di.module

import com.leo.android.project.test.idling.EspressoIdlingResource
import com.leo.android.project.test.idling.impl.EspressoNetworkIdling
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal abstract class TestEspressoModule {

    @Binds
    @Singleton
    abstract fun espressoIdlingResource(idlingResource: EspressoNetworkIdling): EspressoIdlingResource
}