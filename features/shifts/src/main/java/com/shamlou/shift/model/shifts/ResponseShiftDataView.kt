package com.shamlou.shift.model.shifts

data class ResponseShiftDataView(
    val id: String,
    val job: ResponseJobView,
    val recurringShiftSchedule: ResponseRecurringShiftScheduleView,
    val averageEstimatedEarningsPerHour: String
)