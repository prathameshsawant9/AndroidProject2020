package com.leo.android.project.data.impl.remote

import android.content.Context
import com.leo.android.project.data.model.GenreResponse
import com.leo.android.project.data.model.LoginResponse
import com.leo.android.project.data.repo.remote.RemoteRepository
import com.leo.android.project.test.idling.EspressoIdlingResource
import javax.inject.Inject

/*
 * This class will have retrofit implementation;
 * We will make use of FakeRemoteRepoImpl
 */
class RetrofitRemoteRepoImpl @Inject constructor(idlingResource: EspressoIdlingResource): RemoteRepository{

    private val fakeRemoteRepoImpl = FakeRemoteRepoImpl(idlingResource)

    override suspend fun login(username: String, password: String): LoginResponse
            = fakeRemoteRepoImpl.login(username, password)

    override suspend fun getGenres(accessCode: String): GenreResponse
            = fakeRemoteRepoImpl.getGenres(accessCode)

}