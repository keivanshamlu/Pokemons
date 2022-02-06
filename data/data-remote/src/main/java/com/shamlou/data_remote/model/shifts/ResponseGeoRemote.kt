package com.shamlou.data_remote.model.shifts

import com.google.gson.annotations.SerializedName


data class ResponseGeoRemote(
    @SerializedName("lat") var lat: Double? = null,
    @SerializedName("lon") var lon: Double? = null,
)