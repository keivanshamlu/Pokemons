package com.shamlou.domain.model.shifts


data class ResponseRecurringShiftScheduleDomain(
    val id: String,
    val startsAt: String,
    val endsAt: String
)