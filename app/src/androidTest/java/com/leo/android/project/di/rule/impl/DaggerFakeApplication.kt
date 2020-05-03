package com.leo.android.project.di.rule.impl

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.leo.android.project.data.impl.remote.FakeRemoteRepoImpl
import com.leo.android.project.di.component.DaggerTestAppComponent
import com.leo.android.project.di.rule.DaggerTestApplication
import com.leo.android.project.ui.main.MainApplication

class DaggerFakeApplication : DaggerTestApplication(){
    init {
        val app = ApplicationProvider.getApplicationContext<Context>() as MainApplication
        testAppComponent =
            DaggerTestAppComponent
                .factory()
                .create(app, FakeRemoteRepoImpl(idlingResource))

        testAppComponent.inject(app)
    }
}