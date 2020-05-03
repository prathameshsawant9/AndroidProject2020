package com.leo.android.project.di.rule.impl

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.leo.android.project.data.repo.remote.RemoteRepository
import com.leo.android.project.di.component.DaggerTestAppComponent
import com.leo.android.project.di.rule.DaggerTestApplication
import com.leo.android.project.ui.main.MainApplication
import org.mockito.Mockito.mock

class DaggerMockApplication : DaggerTestApplication(){
    val mockedRemote = mock(RemoteRepository::class.java)

    init {
        val app = ApplicationProvider.getApplicationContext<Context>() as MainApplication
        testAppComponent =
            DaggerTestAppComponent
                .factory()
                .create(app, mockedRemote)

        testAppComponent.inject(app)
    }
}