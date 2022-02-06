package com.shamlou.shift.mappers

import com.shamlou.bases.mapper.Mapper
import com.shamlou.navigation.model.NavModelMap
import com.shamlou.shift.MainCoroutineRule
import com.shamlou.shift.ValidGetShiftsResponse.validResponseNavModel
import com.shamlou.shift.ValidGetShiftsResponse.validResponseShiftsView
import com.shamlou.shift.mappers.shifts.MapperShiftViewToNavModel
import com.shamlou.shift.model.shifts.ResponseShiftDataView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MapperShiftViewToNavModelUnitTest(){

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val mapper : Mapper<ResponseShiftDataView, NavModelMap> = MapperShiftViewToNavModel()

    @Test
    @ExperimentalCoroutinesApi
    fun map_shouldReturnCompatibleResponseWhenValidInput() = mainCoroutineRule.runBlockingTest {
        //when
        val response = mapper.map(validResponseShiftsView.data.first())
        //then
        Assert.assertEquals(response.title , validResponseNavModel.title)
        Assert.assertEquals(response.lon , validResponseNavModel.lon , 0.0)
        Assert.assertEquals(response.lat , validResponseNavModel.lat , 0.0)
    }
}
