package com.example.bottomnav.viewModel

import com.example.bottomnav.network.RetrofitBuilderPerson
import com.example.bottomnav.network.response.PersonResponse
import retrofit2.Response
import javax.inject.Inject

class PersonRepositpry @Inject constructor() {
    suspend fun getTrendingPerson(): Response<PersonResponse> {
        return RetrofitBuilderPerson.getAPIServicePerson().getPersonTrending()
    }
}