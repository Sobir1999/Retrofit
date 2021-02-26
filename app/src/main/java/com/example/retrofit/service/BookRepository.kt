package com.example.retrofit.service

import androidx.lifecycle.MutableLiveData
import com.example.retrofit.model.VolumeResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.lifecycle.LiveData




class BookRepository {

    private val BASE_URL = "https://www.googleapis.com/"
    private var bookSearchService: BookSearchService ?= null
    private var volumeResponseLiveData: MutableLiveData<VolumeResponse> ?= null

    init {
        volumeResponseLiveData = MutableLiveData<VolumeResponse>()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        bookSearchService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookSearchService::class.java)
    }

    fun searchVolumes(keyword: String, author: String, apiKey: String){
        bookSearchService!!.searchVolumes(keyword, author, apiKey).enqueue(object :
            Callback<VolumeResponse> {
            override fun onResponse(
                call: Call<VolumeResponse>,
                response: Response<VolumeResponse>
            ) {
                if (response.isSuccessful || response.body() != null) {
                    volumeResponseLiveData!!.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<VolumeResponse>, t: Throwable) {
            }

        })
    }
    fun getVolumesResponseLiveData(): LiveData<VolumeResponse>? {
        return volumeResponseLiveData
    }
}