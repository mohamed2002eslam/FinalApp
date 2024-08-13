package com.example.bottomnav

import com.example.bottomnav.Screens.homeScreen.MoviesRepository
import com.example.bottomnav.Screens.profileScreen.PersonRepositpry
import com.example.bottomnav.network.Api
import com.example.bottomnav.network.ApiPerson
import com.example.bottomnav.network.NetworkConstants
import com.example.bottomnav.network.NetworkConstants.TIME_30
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun providApi(okhttpClient: OkHttpClient):ApiPerson{
        val retrofit = Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient)
            .build()
        val api = retrofit.create(ApiPerson::class.java)
        return api
    }
    @Provides
    @Singleton
    fun providPersonRepo(apiPerson: ApiPerson): PersonRepositpry {
        return PersonRepositpry(apiPerson)
    }
    @Provides
    @Singleton
    fun providApiMovies(okhttpClient: OkHttpClient):Api{
        val retrofit = Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient)
            .build()
        val api = retrofit.create(Api::class.java)
        return api
    }
    @Provides
    @Singleton
    fun providMovieRepo(apiMovie: Api): MoviesRepository {
        return MoviesRepository(apiMovie)
    }
    @Provides
    @Singleton
     fun createOkHTTPClient(): OkHttpClient {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(BODY)
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)//can show me in log cat the response of the api
            .readTimeout(TIME_30, SECONDS)//when api do not response
            .writeTimeout(TIME_30, SECONDS)//when api do not send acception for connection
            .connectTimeout(TIME_30, SECONDS)//
            .build()
    }
}
