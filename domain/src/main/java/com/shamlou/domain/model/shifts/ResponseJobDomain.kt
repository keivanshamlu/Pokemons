package com.shamlou.domain.model.shifts

data class ResponseJobDomain(
    val id: String,
    val title: String,
    val extraBriefing: String,
    val lat: Double,
    val lon: Double
)