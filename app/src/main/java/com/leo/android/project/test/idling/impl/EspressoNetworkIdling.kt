package com.leo.android.project.test.idling.impl

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource
import com.leo.android.project.test.idling.EspressoIdlingResource
import javax.inject.Inject

class EspressoNetworkIdling @Inject constructor():
    EspressoIdlingResource {

    companion object{
        val idlingResource = CountingIdlingResource("GLOBAL")
    }

    override fun increment() {
        idlingResource.increment()
    }

    override fun decrement() {
        if (!idlingResource.isIdleNow)
            idlingResource.decrement()
    }

    override fun getResource(): IdlingResource = idlingResource
}