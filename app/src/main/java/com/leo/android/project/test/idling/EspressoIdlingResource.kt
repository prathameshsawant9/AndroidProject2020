package com.leo.android.project.test.idling

import androidx.test.espresso.IdlingResource

interface EspressoIdlingResource{
    fun increment()
    fun decrement()
    fun getResource(): IdlingResource
}