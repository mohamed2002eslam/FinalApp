package com.example.bottomnav.viewModel

import com.example.bottomnav.network.Api
import com.example.bottomnav.network.RetrofitBuilder
import com.example.bottomnav.network.response.MoviesResponse
import retrofit2.Response
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val api:Api) {
    suspend fun getTrendingMovies(): Response<MoviesResponse> {
        return api.getMoviesTrending()
    }
}