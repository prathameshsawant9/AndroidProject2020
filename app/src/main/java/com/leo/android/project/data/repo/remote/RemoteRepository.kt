package com.leo.android.project.data.repo.remote

import com.leo.android.project.data.model.GenreResponse
import com.leo.android.project.data.model.LoginResponse

interface RemoteRepository {
    suspend fun login(username: String, password: String): LoginResponse
    suspend fun getGenres(accessCode: String): GenreResponse
}