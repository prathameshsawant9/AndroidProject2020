package com.leo.android.project.data.impl.local.pref

import android.content.Context
import com.leo.android.project.data.repo.local.pref.PreferenceRepository
import javax.inject.Inject

class SharedPreferenceRepoImpl @Inject constructor(val context: Context) : PreferenceRepository{

    private val sharedPreference = context.getSharedPreferences("Project2020", 0)
    private val sharedEditor = sharedPreference.edit()

    override var accessCode: String
        get() = sharedPreference.getString("accessCode", "").toString()
        set(value) {
            sharedEditor.putString("accessCode", value).commit()
        }
}