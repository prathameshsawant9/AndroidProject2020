package com.leo.android.project.di.rule

import com.leo.android.project.di.component.TestAppComponent
import com.leo.android.project.test.idling.impl.EspressoNetworkIdling

abstract class DaggerTestApplication{
    val idlingResource = EspressoNetworkIdling()
    lateinit var testAppComponent: TestAppComponent
}