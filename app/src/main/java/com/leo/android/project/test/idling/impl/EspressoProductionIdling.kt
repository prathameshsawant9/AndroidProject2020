package com.leo.android.project.test.idling.impl

import androidx.test.espresso.IdlingResource
import com.leo.android.project.test.idling.EspressoIdlingResource
import javax.inject.Inject

/*
 * Intended for production only
 */
class EspressoProductionIdling @Inject constructor() : EspressoIdlingResource {
    override fun increment() {}
    override fun decrement() {}
    override fun getResource(): IdlingResource {
        TODO("This method shall not be called anywhere")
    }
}