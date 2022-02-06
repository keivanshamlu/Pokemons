package com.shamlou.data_remote.model.shifts

import com.google.gson.annotations.SerializedName

data class ResponseReportAtAddressRemote(
    @SerializedName("geo") var geo: ResponseGeoRemote? = null,
)