package com.leo.android.project.di.module

import com.leo.android.project.ui.login.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun injectLoginFragment(): LoginFragment
}