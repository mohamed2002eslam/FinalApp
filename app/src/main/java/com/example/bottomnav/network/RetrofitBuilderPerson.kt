package com.example.bottomnav.network


import com.example.bottomnav.network.NetworkConstants.TIME_30
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.SECONDS

class RetrofitBuilderPerson {
    companion object {
        fun getAPIServicePerson(): ApiPerson {
            val retrofit = Retrofit.Builder()
                .baseUrl(NetworkConstantsPerson.BASE_URL_Person)
                .addConverterFactory(GsonConverterFactory.create())
                .client(createOkHTTPClient())
                .build()
            val api = retrofit.create(ApiPerson::class.java)
            return api
        }

        //Response time out for when the service do not send
        private fun createOkHTTPClient(): OkHttpClient {
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

}