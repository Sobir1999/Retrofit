package com.example.retrofit.service

import android.telecom.Call
import com.example.retrofit.model.VolumeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BookSearchService {

    @GET("/bookks/v1/volumes")
    fun searchVolumes(
        @Query("q")
        query: String,

        @Query("inauthor")
        author: String,

        @Query("key")
        apiKey: String
    ): retrofit2.Call<VolumeResponse>
}