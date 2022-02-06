package com.shamlou.domain.model.shifts

data class ResponseShiftsDomain(
    val data: List<ResponseShiftDataDomain> = listOf(),
    val aggregations: ResponseAggregationsDomain
)