package com.example.loodoscase.api

import com.example.loodoscase.model.MovieInfoResponse
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    @POST("/")
    fun getMovieInfo(@Query("t") movie_name: String,
                  @Query("apikey") api_key: String) : Observable<MovieInfoResponse>
}