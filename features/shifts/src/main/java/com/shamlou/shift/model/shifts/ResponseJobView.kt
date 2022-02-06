package com.shamlou.shift.model.shifts


data class ResponseJobView(
    val id: String,
    val title: String,
    val extraBriefing: String,
    val lat : Double,
    val lon : Double
)