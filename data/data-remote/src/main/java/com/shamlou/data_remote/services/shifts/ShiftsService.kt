package com.shamlou.data_remote.services.shifts

import com.shamlou.data_remote.model.shifts.ResponseShiftsRemote
import retrofit2.http.GET
import retrofit2.http.Query

interface ShiftsService {

    @GET("/api/v3/shifts")
    suspend fun getShifts(@Query(value = "filter[date]" , encoded = true) data : String): ResponseShiftsRemote
}