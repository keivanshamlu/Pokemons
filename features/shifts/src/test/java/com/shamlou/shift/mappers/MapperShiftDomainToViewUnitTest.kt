package com.shamlou.shift.mappers

import com.shamlou.bases.mapper.Mapper
import com.shamlou.domain.model.shifts.ResponseShiftsDomain
import com.shamlou.navigation.model.NavModelMap
import com.shamlou.shift.MainCoroutineRule
import com.shamlou.shift.ValidGetShiftsResponse.validResponseNavModel
import com.shamlou.shift.ValidGetShiftsResponse.validResponseShiftsDomain
import com.shamlou.shift.ValidGetShiftsResponse.validResponseShiftsView
import com.shamlou.shift.mappers.shifts.MapperShiftDomainToView
import com.shamlou.shift.mappers.shifts.MapperShiftViewToNavModel
import com.shamlou.shift.model.shifts.ResponseShiftDataView
import com.shamlou.shift.model.shifts.ResponseShiftsView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test


class MapperShiftDomainToViewUnitTest(){

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val mapper : Mapper<ResponseShiftsDomain, ResponseShiftsView> = MapperShiftDomainToView()

    @Test
    @ExperimentalCoroutinesApi
    fun map_shouldReturnCompatibleResponseWhenValidInput() = mainCoroutineRule.runBlockingTest {
        //when
        val response = mapper.map(validResponseShiftsDomain)
        //then
        Assert.assertEquals(response , validResponseShiftsView)
    }
}