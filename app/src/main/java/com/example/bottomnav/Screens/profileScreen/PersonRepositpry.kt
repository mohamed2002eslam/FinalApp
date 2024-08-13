package com.example.bottomnav.Screens.profileScreen

import com.example.bottomnav.network.ApiPerson
import com.example.bottomnav.network.response.PersonResponse
import retrofit2.Response
import javax.inject.Inject

class PersonRepositpry @Inject constructor(private val api: ApiPerson) {
    suspend fun getTrendingPerson(): Response<PersonResponse> {
        return api.getPersonTrending()
    }
}