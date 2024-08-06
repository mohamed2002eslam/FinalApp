package com.example.bottomnav.network.response

import com.google.gson.annotations.SerializedName

data class MoviesResponse(

    @SerializedName("page")
    val page:Int?=null,
    @SerializedName("results")
    val results: ArrayList<MoviesResponse>?=null,
    @SerializedName("adult")
    val isAdult:Boolean ?=null,
    @SerializedName("vote_average")
    val averageVote:Double?=null,
    @SerializedName("original_name")
    val name:String?=null,
    @SerializedName("poster_path")
    val posterPath: String?=null,
    @SerializedName("overview")
    val overview: String?=null
)