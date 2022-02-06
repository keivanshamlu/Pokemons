package com.shamlou.data_remote.model.shifts

import com.google.gson.annotations.SerializedName


data class ResponseShiftDataRemote(

    @SerializedName("id") var id: String? = null,
    @SerializedName("job") var job: ResponseJobRemote? = null,
    @SerializedName("recurring_shift_schedule") var recurringShiftSchedule: ResponseRecurringShiftScheduleRemote? = null,
    @SerializedName("average_estimated_earnings_per_hour") var averageEstimatedEarningsPerHour: ResponseAverageEstimatedEarningsPerHourRemote? = null
)
