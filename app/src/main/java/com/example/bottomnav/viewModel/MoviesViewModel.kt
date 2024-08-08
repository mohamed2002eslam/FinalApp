package com.example.bottomnav.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomnav.network.RetrofitBuilder
import com.example.bottomnav.network.response.MoviesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MoviesRepository) : ViewModel() {
    var moviesState = mutableStateOf<MoviesResponse?>(null)
        private set

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getTrendingMovies()
                if (response.isSuccessful) {
                    moviesState.value = response.body()
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}