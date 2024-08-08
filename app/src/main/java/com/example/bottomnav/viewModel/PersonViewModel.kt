package com.example.bottomnav.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomnav.network.RetrofitBuilder
import com.example.bottomnav.network.response.MoviesResponse
import com.example.bottomnav.network.response.PersonResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PersonViewModel @Inject constructor(private val repository: PersonRepositpry) : ViewModel() {
    var PersonState = mutableStateOf<PersonResponse?>(null)
        private set

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getTrendingPerson()
                if (response.isSuccessful) {
                    PersonState.value = response.body()
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}