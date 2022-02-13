package com.example.retrofit2_example.api

import com.example.retrofit2_example.api.ApiUtilits.API_KEY
import com.example.retrofit2_example.model.ImageModel
import com.example.retrofit2_example.model.SearchModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {
    @Headers("Authorization: Client-ID " + API_KEY)
    @GET("/photos")
    fun getImages(@Query("page") page: Int, @Query("per_page") perPage: Int): Call<List<ImageModel>>

    @Headers("Authorization: Client-ID " + API_KEY)
    @GET("/search/photos")
    fun searchImage(@Query("query") query: String,@Query("per_page") perPage: Int): Call<SearchModel>
}