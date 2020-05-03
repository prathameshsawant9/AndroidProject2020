package com.leo.android.project.data.impl.remote

import com.leo.android.project.data.model.Genre
import com.leo.android.project.data.model.GenreResponse
import com.leo.android.project.data.model.LoginResponse
import com.leo.android.project.data.model.Song
import com.leo.android.project.data.repo.remote.RemoteRepository
import com.leo.android.project.test.idling.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class FakeRemoteRepoImpl @Inject constructor(val testIdlingResource: EspressoIdlingResource): RemoteRepository{

    private val ioThread = Dispatchers.IO

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
        withContext(ioThread){

            testIdlingResource.increment()

            // replicating network like delay
            delay(2_000)

            testIdlingResource.decrement()

            LoginResponse(accessCode).apply {
                statusCode = if (!username.equals("leo", true)){
                    message = "Invalid Username or Password. Please Try Again"
                    200
                } else 200
            }
        }

    override suspend fun getGenres(paramAccessCode: String): GenreResponse =
        withContext(ioThread){

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