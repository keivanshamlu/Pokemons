package com.shamlou.data_remote.mappers

import com.shamlou.bases.mapper.Mapper
import com.shamlou.data.model.shifts.ResponseShiftsData
import com.shamlou.data_remote.model.shifts.ResponseShiftsRemote
import com.shamlou.data_remote.utility.ValidGetShiftsResponse.validResponseShiftsData
import com.shamlou.data_remote.utility.ValidGetShiftsResponse.validResponseShiftsRemote
import com.shamlou.data_remote.utility.MainCoroutineRule
import com.shamlou.data_remote.utility.ValidGetShiftsResponse.validResponseShiftsEmpty
import com.shamlou.data_remote.utility.ValidGetShiftsResponse.validResponseShiftsRemoteNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MapperShiftDataToDomainUnitTest(){

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val mapper : Mapper<ResponseShiftsRemote, ResponseShiftsData> = MapperShiftRemoteToData()

    @Test
    @ExperimentalCoroutinesApi
    fun map_shouldReturnCompatibleResponseWhenValidIput() = mainCoroutineRule.runBlockingTest {
        //when
        val response = mapper.map(validResponseShiftsRemote)
        //then
        Assert.assertEquals(response , validResponseShiftsData)
    }
    @Test
    @ExperimentalCoroutinesApi
    fun map_shouldReturnEmptyResponseWhenNullInput() = mainCoroutineRule.runBlockingTest {
        //when
        val response = mapper.map(validResponseShiftsRemoteNull)
        //then
        Assert.assertEquals(response , validResponseShiftsEmpty)
    }
}