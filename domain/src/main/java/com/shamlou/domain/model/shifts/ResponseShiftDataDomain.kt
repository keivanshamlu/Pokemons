package com.shamlou.domain.model.shifts


data class ResponseShiftDataDomain(
    val id: String,
    val job: ResponseJobDomain,
    val recurringShiftSchedule: ResponseRecurringShiftScheduleDomain,
    val averageEstimatedEarningsPerHour: ResponseAverageEstimatedEarningsPerHourDomain
)