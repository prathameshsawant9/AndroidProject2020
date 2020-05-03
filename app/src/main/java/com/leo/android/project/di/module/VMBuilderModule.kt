package com.leo.android.project.di.module

import androidx.lifecycle.ViewModelProvider
import com.leo.android.project.ui.main.factory.VMFactory
import dagger.Binds
import dagger.Module

@Module
internal abstract class VMBuilderModule {

    @Binds
    abstract fun viewModelFactory(factory: VMFactory): ViewModelProvider.Factory
}