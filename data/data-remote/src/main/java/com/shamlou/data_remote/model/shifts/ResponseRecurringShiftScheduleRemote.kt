package com.shamlou.data_remote.model.shifts

import com.google.gson.annotations.SerializedName


data class ResponseRecurringShiftScheduleRemote(

    @SerializedName("id") var id: String? = null,
    @SerializedName("starts_at") var startsAt: String? = null,
    @SerializedName("ends_at") var endsAt: String? = null,
)
