package com.example.retrofit.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.retrofit.model.VolumeResponse
import com.example.retrofit.service.BookRepository
import io.github.cdimascio.dotenv.Dotenv

class BookSearchViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var bookRepository: BookRepository
    private var volumeResponseLiveData: LiveData<VolumeResponse> ?= null

    init {
        bookRepository = BookRepository()
        volumeResponseLiveData = bookRepository.getVolumesResponseLiveData()
    }

    fun searchVolumes(keyword: String, author: String){
        val dotenv = Dotenv.configure().directory("/assets").filename("env").load()
        bookRepository.searchVolumes(keyword, author, dotenv.get("GOOGLE_API_KEY"))
    }

    fun getVolumesResponseLiveData(): LiveData<VolumeResponse>? {
        return volumeResponseLiveData
    }
}