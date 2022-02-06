package com.shamlou.data_remote.dataSources

import com.shamlou.bases.mapper.Mapper
import com.shamlou.bases.readWrite.Readable
import com.shamlou.data.model.shifts.ResponseShiftsData
import com.shamlou.data_remote.model.shifts.ResponseShiftsRemote
import com.shamlou.data_remote.services.shifts.ShiftsService
import javax.inject.Inject

/**
 * makes a network call and gets
 * shift data and then maps it
 * into data objects and returns it
 */
class RemoteShiftsDataSource @Inject constructor(
    private val mediaSetService: ShiftsService,
    private val mapperDataToLocal: Mapper<ResponseShiftsRemote, ResponseShiftsData>
) : Readable<String, ResponseShiftsData> {
    override suspend fun read(input: String): ResponseShiftsData {

        return mediaSetService.getShifts(input).let { mapperDataToLocal.map(it) }
    }
}

