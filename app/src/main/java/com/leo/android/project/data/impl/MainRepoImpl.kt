package com.leo.android.project.data.impl

import com.leo.android.project.data.model.GenreResponse
import com.leo.android.project.data.model.LoginResponse
import com.leo.android.project.data.repo.MainRepository
import com.leo.android.project.data.repo.local.db.DBRepository
import com.leo.android.project.data.repo.local.pref.PreferenceRepository
import com.leo.android.project.data.repo.remote.RemoteRepository
import javax.inject.Inject

class MainRepoImpl @Inject constructor(
                            val remote: RemoteRepository,
                            val db: DBRepository,
                            val pref: PreferenceRepository) : MainRepository{

    override suspend fun login(username: String, password: String): LoginResponse =
        remote.login(username, password)

    override suspend fun getGenres(accessCode: String): GenreResponse =
        remote.getGenres(accessCode)
}