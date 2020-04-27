package com.leo.android.project.data.impl.remote

import com.leo.android.project.data.model.Genre
import com.leo.android.project.data.model.GenreResponse
import com.leo.android.project.data.model.LoginResponse
import com.leo.android.project.data.model.Song
import com.leo.android.project.data.repo.remote.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class FakeRemoteRepoImpl @Inject constructor(): RemoteRepository{

    /*
     * Random access code
     */
    private val accessCode: String = UUID.randomUUID().toString()

    /*
     * Dummy genre list
     */
    private val genreList = listOf(
        Genre(1, "Rock", listOf(Song("Metallica"), Song("Pink Floyd"))),
        Genre(2,"Pop", listOf(Song("Michael Jackson"), Song("Beyonce")))
    )

    override suspend fun login(username: String, password: String): LoginResponse =
        withContext(Dispatchers.IO){

            LoginResponse(accessCode).apply {
                statusCode = 200
            }

        }

    override suspend fun getGenres(paramAccessCode: String): GenreResponse =
        withContext(Dispatchers.IO){

            GenreResponse(genreList).apply {
                if(paramAccessCode.equals(accessCode, false)){
                    statusCode = 200
                    message = ""
                }
                else {
                    statusCode = 400
                    message = "Access Denied"
                    genres = listOf()
                }
            }

        }
}