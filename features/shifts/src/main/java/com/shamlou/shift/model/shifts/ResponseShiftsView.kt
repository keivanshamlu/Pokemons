package com.shamlou.shift.model.shifts

data class ResponseShiftsView(
    val data: List<ResponseShiftDataView> = listOf(),
    val aggregations: String
)