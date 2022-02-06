package com.shamlou.data.model.shifts

data class ResponseShiftDataData(

    val id: String,
    val job: ResponseJobData,
    val recurringShiftSchedule: ResponseRecurringShiftScheduleData,
    val averageEstimatedEarningsPerHour: ResponseAverageEstimatedEarningsPerHourData
)