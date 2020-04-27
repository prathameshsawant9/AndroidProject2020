package com.leo.android.project.ui.base.livedata

import androidx.annotation.MainThread
import androidx.annotation.Nullable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import timber.log.Timber
import java.util.concurrent.atomic.AtomicBoolean

class NotifyLiveData : MutableLiveData<Event>() {

    private val mPending: AtomicBoolean = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in Event>) {
        if (hasActiveObservers()){
            Timber.i("Multiple observers registered but only one will be notified of changes.")
        }

        super.observe(owner, Observer {
            if (mPending.compareAndSet(true, false)){
                it?.let {
                    // Only, If Content has not been handled.
                    if (!it.isContentHandled()){
                        observer.onChanged(it)
                    }
                }
            }
        })
    }

    @MainThread
    override fun setValue(@Nullable t: Event) {
        mPending.set(true)
        super.setValue(t)
    }
}