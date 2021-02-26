package com.example.retrofit.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VolumeResponse{

    @SerializedName("kind")
    @Expose
    private val kind: String ?= null

    @SerializedName("items")
    @Expose
    private val items: List<Volume> ?= null

    @SerializedName("totalItems")
    @Expose
    private val totalItems : Int ?= 0

    fun getKind(): String? {
        return kind
    }

    fun getItems(): List<Volume?>? {
        return items
    }

    fun getTotalItems(): Int? {
        return totalItems
    }
}