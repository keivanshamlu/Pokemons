package com.shamlou.data.model.shifts

data class ResponseShiftsData(

    val data: List<ResponseShiftDataData> = listOf(),
    val aggregations: ResponseAggregationsData
)