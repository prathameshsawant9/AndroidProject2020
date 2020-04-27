package com.leo.android.project.ui.base.livedata

sealed class Event{
    protected var hasBeenHandled = false
    fun isContentHandled()= hasBeenHandled
}

sealed class NotifyEvent<E: Any>(val flow: Int = 0,val data: E) : Event(){

    constructor(): this(0, "" as E)

    fun data() {
        hasBeenHandled = true
        data
    }

    fun flow() = flow
}

class LoadingEvent<E: Any>(flow: Int, data: E): NotifyEvent<E>(flow, data)
class FailureEvent<E: Any>(flow: Int, data: E): NotifyEvent<E>(flow, data)
class SuccessEvent<E: Any>(flow: Int, data: E): NotifyEvent<E>(flow, data)
