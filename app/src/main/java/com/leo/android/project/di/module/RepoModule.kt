package com.leo.android.project.di.module

import com.leo.android.project.data.impl.MainRepoImpl
import com.leo.android.project.data.impl.local.db.RoomDBRepoImpl
import com.leo.android.project.data.impl.local.pref.SharedPreferenceRepoImpl
import com.leo.android.project.data.impl.remote.RetrofitRemoteRepoImpl
import com.leo.android.project.data.repo.MainRepository
import com.leo.android.project.data.repo.local.db.DBRepository
import com.leo.android.project.data.repo.local.pref.PreferenceRepository
import com.leo.android.project.data.repo.remote.RemoteRepository
import dagger.Binds
import dagger.Module

@Module
interface RepoModule{

    @Binds
    fun dbRepository(dbRepository: RoomDBRepoImpl): DBRepository

    @Binds
    fun sharedPrefRepository(sharedPrefsRepoImpl: SharedPreferenceRepoImpl): PreferenceRepository

    @Binds
    fun remoteRepository(remoteRepo: RetrofitRemoteRepoImpl): RemoteRepository

    @Binds
    fun provideRepository(repo: MainRepoImpl): MainRepository
}