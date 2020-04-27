package com.leo.android.project.data.impl.remote

import android.content.Context
import com.leo.android.project.data.model.Genre
import com.leo.android.project.data.model.GenreResponse
import com.leo.android.project.data.model.LoginResponse
import com.leo.android.project.data.model.Song
import com.leo.android.project.data.repo.remote.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

/*
 * This class needs to have retrofit implementation;
 * We will make use of FakeRemoteRepoImpl
 */
class RetrofitRemoteRepoImpl @Inject constructor(val context: Context): RemoteRepository{

    private val fakeRemoteRepoImpl = FakeRemoteRepoImpl()

    override suspend fun login(username: String, password: String): LoginResponse
            = fakeRemoteRepoImpl.login(username, password)

    override suspend fun getGenres(accessCode: String): GenreResponse
            = fakeRemoteRepoImpl.getGenres(accessCode)

}