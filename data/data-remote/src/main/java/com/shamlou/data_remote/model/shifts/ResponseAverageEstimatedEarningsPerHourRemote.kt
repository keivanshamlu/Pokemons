package com.shamlou.data_remote.model.shifts

import com.google.gson.annotations.SerializedName


data class ResponseAverageEstimatedEarningsPerHourRemote(

    @SerializedName("currency") var currency: String? = null,
    @SerializedName("amount") var amount: Float? = null
)