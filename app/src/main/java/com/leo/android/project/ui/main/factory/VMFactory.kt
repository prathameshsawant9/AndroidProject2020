package com.leo.android.project.ui.main.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leo.android.project.data.repo.MainRepository
import com.leo.android.project.ui.login.LoginViewModel
import javax.inject.Inject

class VMFactory @Inject constructor(private val mainRepo: MainRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when{
        modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
            LoginViewModel(mainRepo) as T
        }
        else -> {
            throw IllegalArgumentException("VM not listed")
        }
    }
}