package com.leo.android.project.ui.base

import androidx.lifecycle.ViewModel
import com.leo.android.project.ui.base.livedata.Event
import com.leo.android.project.ui.base.livedata.NotifyLiveData

open class BaseViewModel : ViewModel() {
    private var commandDelegate = NotifyLiveData()
    fun events() = commandDelegate
    fun notify(event: Event) {
        commandDelegate.setValue(event)
    }
}