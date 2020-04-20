package com.example.loodoscase.model

import java.io.Serializable

data class MovieInfoResponse(
    val Title: String,
    val Year: String,
    val Runtime: String,
    val Genre: String,
    val Director: String,
    val Writer: String,
    val Actors: String,
    val Plot: String,
    val Language: String,
    val Awards: String,
    val imbdRating: String,
    val Production: String,
    val Poster: String,
    val imdbRating: String,
    val Response: String
): Serializable