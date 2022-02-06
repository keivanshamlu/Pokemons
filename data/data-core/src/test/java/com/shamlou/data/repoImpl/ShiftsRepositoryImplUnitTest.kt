package com.shamlou.data.repoImpl

import com.shamlou.bases.mapper.Mapper
import com.shamlou.bases.readWrite.Readable
import com.shamlou.data.model.shifts.ResponseShiftsData
import com.shamlou.data.utility.MainCoroutineRule
import com.shamlou.data.utility.ValidGetShiftsResponse.sampleDate
import com.shamlou.data.utility.ValidGetShiftsResponse.sampleError
import com.shamlou.data.utility.ValidGetShiftsResponse.validResponseShiftsData
import com.shamlou.data.utility.ValidGetShiftsResponse.validResponseShiftsDomain
import com.shamlou.domain.model.shifts.ResponseShiftsDomain
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class ShiftsRepositoryImplUnitTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var repo: ShiftsRepositoryImpl

    @MockK(relaxed = true)
    lateinit var shiftsRemoteReadable: Readable<String, ResponseShiftsData>

    @MockK(relaxed = true)
    lateinit var mapperSiftsDataToDomain: Mapper<ResponseShiftsData, ResponseShiftsDomain>

    private fun setUpValidStubs(){
        coEvery { mapperSiftsDataToDomain.map(any()) } returns validResponseShiftsDomain
        coEvery { shiftsRemoteReadable.read(sampleDate) } returns validResponseShiftsData
    }
    @Before
    fun setUp() {

        MockKAnnotations.init(this)
        repo = ShiftsRepositoryImpl(
            shiftsRemoteReadable,
            mapperSiftsDataToDomain
        )
    }

    @Test
    @ExperimentalCoroutinesApi
    fun getAllShifts_shouldReturnCompatibleDomainModel_whenRemoteReturnValidDataModel() = mainCoroutineRule.runBlockingTest {

        //given
        setUpValidStubs()

        //when
        val response = repo.getShifts(sampleDate).first()

        //then
        verify { mapperSiftsDataToDomain.map(validResponseShiftsData) }
        Assert.assertEquals(response , validResponseShiftsDomain)
    }
    @Test
    @ExperimentalCoroutinesApi
    fun getAllShifts_shouldReturnCompatibleDomainModel_whenRemoteThrowError() = mainCoroutineRule.runBlockingTest {

        //given
        setUpValidStubs()
        coEvery { shiftsRemoteReadable.read(sampleDate) } throws sampleError

        try {
            //when
            repo.getShifts(sampleDate).first()
        }catch (e : Exception){
            //then
            Assert.assertEquals(e.javaClass, sampleError.javaClass)
            Assert.assertEquals(e.message, sampleError.message)
        }
    }
}