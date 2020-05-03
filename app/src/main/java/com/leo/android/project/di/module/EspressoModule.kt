package com.leo.android.project.di.module

import com.leo.android.project.test.idling.EspressoIdlingResource
import com.leo.android.project.test.idling.impl.EspressoProductionIdling
import dagger.Binds
import dagger.Module

@Module
internal abstract class EspressoModule {

    @Binds
    abstract fun espressoIdlingResource(idlingResource: EspressoProductionIdling): EspressoIdlingResource
}