package com.example.bottomnav.Screens.homeScreen

import com.example.bottomnav.network.Api
import com.example.bottomnav.network.response.MoviesResponse
import retrofit2.Response
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val api:Api) {
    suspend fun getTrendingMovies(): MoviesResponse? {
        val response=api.getMoviesTrending()
        return if (response.isSuccessful){
            response.body()
        }else{
            null
        }
    }
}