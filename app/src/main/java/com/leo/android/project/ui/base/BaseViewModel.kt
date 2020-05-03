package com.leo.android.project.ui.base

import androidx.lifecycle.ViewModel
import com.leo.android.project.data.model.Response
import com.leo.android.project.ui.base.livedata.Event
import com.leo.android.project.ui.base.livedata.FailureEvent
import com.leo.android.project.ui.base.livedata.NotifyLiveData
import kotlinx.coroutines.CoroutineExceptionHandler

open class BaseViewModel : ViewModel() {
    open var exceptionHandler = CoroutineExceptionHandler { _,  throwable ->
        notify(FailureEvent(0, Response().apply {
            statusCode = Integer.MAX_VALUE
            message = throwable.message.toString()
        }))
    }

    private var commandDelegate = NotifyLiveData()
    fun events() = commandDelegate
    fun notify(event: Event) {
        commandDelegate.setValue(event)
    }
}