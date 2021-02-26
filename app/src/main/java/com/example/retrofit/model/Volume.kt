package com.example.retrofit.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Volume {
    
    @SerializedName("kind")
    @Expose
    private val kind: String ?= null

    @SerializedName("id")
    @Expose
    private val id: String ?= null

    @SerializedName("etag")
    @Expose
    private val etag: String ?= null

    @SerializedName("selfLink")
    @Expose
    private val selfLink: String ?= null

    @SerializedName("volumeInfo")
    @Expose
    private val volumeInfo: VolumeInfo ?= null

    fun getKind(): String? {
        return kind
    }

    fun getId(): String? {
        return id
    }

    fun getEtag(): String? {
        return etag
    }

    fun getSelfLink(): String? {
        return selfLink
    }

    fun getVolumeInfo(): VolumeInfo? {
        return volumeInfo
    }
}