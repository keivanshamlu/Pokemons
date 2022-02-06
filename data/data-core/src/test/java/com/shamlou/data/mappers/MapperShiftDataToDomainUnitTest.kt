package com.shamlou.data.mappers

import com.shamlou.bases.mapper.Mapper
import com.shamlou.data.mappers.shifts.MapperShiftDataToDomain
import com.shamlou.data.model.shifts.ResponseShiftsData
import com.shamlou.data.utility.MainCoroutineRule
import com.shamlou.data.utility.ValidGetShiftsResponse.validResponseShiftsData
import com.shamlou.data.utility.ValidGetShiftsResponse.validResponseShiftsDomain
import com.shamlou.domain.model.shifts.ResponseShiftsDomain
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MapperShiftDataToDomainUnitTest(){

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val mapper : Mapper<ResponseShiftsData, ResponseShiftsDomain> = MapperShiftDataToDomain()

    @Test
    @ExperimentalCoroutinesApi
    fun map_shouldReturnCompatibleResponseWhenValidIput() = mainCoroutineRule.runBlockingTest {
        //when
        val response = mapper.map(validResponseShiftsData)
        //then
        Assert.assertEquals(response , validResponseShiftsDomain)
    }
}