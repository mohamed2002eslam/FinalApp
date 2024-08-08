package com.example.bottomnav.viewModel

import com.example.bottomnav.network.RetrofitBuilder
import com.example.bottomnav.network.response.MoviesResponse
import retrofit2.Response
import javax.inject.Inject

class MoviesRepository @Inject constructor() {
    suspend fun getTrendingMovies(): Response<MoviesResponse> {
        return RetrofitBuilder.getAPIService().getMoviesTrending()
    }
}