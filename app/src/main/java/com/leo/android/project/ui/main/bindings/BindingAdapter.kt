package com.leo.android.project.ui.main.bindings

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("bindAdapterErrorText")
fun setErrorText(textInputLayout: TextInputLayout, errorMessage: String?) {
    textInputLayout.error = errorMessage
    textInputLayout.isErrorEnabled = !errorMessage.isNullOrEmpty()
}