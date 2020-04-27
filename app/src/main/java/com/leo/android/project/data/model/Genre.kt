package com.leo.android.project.data.model

data class GenreResponse(var genres: List<Genre>) : Response()

data class Song(val name: String)

data class Genre(val id: Int,
                 val name: String,
                 val songs: List<Song>)