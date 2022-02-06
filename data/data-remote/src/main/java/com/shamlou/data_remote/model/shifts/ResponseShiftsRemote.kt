package com.shamlou.data_remote.model.shifts

import com.google.gson.annotations.SerializedName


data class ResponseShiftsRemote(

    @SerializedName("data") var data: List<ResponseShiftDataRemote>? = null,
    @SerializedName("aggregations") var aggregations: ResponseAggregationsRemote? = null
)
