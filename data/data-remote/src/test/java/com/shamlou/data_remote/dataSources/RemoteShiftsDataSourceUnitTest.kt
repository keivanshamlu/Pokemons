package com.shamlou.data_remote.dataSources

import com.shamlou.bases.mapper.Mapper
import com.shamlou.data.model.shifts.ResponseShiftsData
import com.shamlou.data_remote.model.shifts.ResponseShiftsRemote
import com.shamlou.data_remote.services.shifts.ShiftsService
import com.shamlou.data_remote.utility.MainCoroutineRule
import com.shamlou.data_remote.utility.ValidGetShiftsResponse.sampleDate
import com.shamlou.data_remote.utility.ValidGetShiftsResponse.sampleError
import com.shamlou.data_remote.utility.ValidGetShiftsResponse.validResponseShiftsData
import com.shamlou.data_remote.utility.ValidGetShiftsResponse.validResponseShiftsRemote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class RemoteShiftsDataSourceUnitTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var dataSource: RemoteShiftsDataSource

    @MockK
    lateinit var mediaSetService: ShiftsService

    @MockK
    lateinit var mapperDataToData: Mapper<ResponseShiftsRemote, ResponseShiftsData>

    @Before
    fun setUp() {

        MockKAnnotations.init(this)
        dataSource = RemoteShiftsDataSource(mediaSetService, mapperDataToData)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun read_shouldCallGetShifts() = mainCoroutineRule.runBlockingTest {

        //given
        every { mapperDataToData.map(any()) } returns validResponseShiftsData
        coEvery { mediaSetService.getShifts(sampleDate) } returns validResponseShiftsRemote

        //when
        dataSource.read(sampleDate)

        //then
        coVerify { mediaSetService.getShifts(sampleDate) }
    }

    @Test
    @ExperimentalCoroutinesApi
    fun read_shouldMapResponse() = mainCoroutineRule.runBlockingTest {

        //given
        every { mapperDataToData.map(any()) } returns validResponseShiftsData
        coEvery { mediaSetService.getShifts(sampleDate) } returns validResponseShiftsRemote
        //when
        dataSource.read(sampleDate)
        //then
        coVerify { mapperDataToData.map(validResponseShiftsRemote) }
    }

    @Test
    @ExperimentalCoroutinesApi
    fun read_shouldMapResponseAndReturnValidMappedResponse() = mainCoroutineRule.runBlockingTest {

        //given
        every { mapperDataToData.map(any()) } returns validResponseShiftsData
        coEvery { mediaSetService.getShifts(sampleDate) } returns validResponseShiftsRemote

        //when
        val respose = dataSource.read(sampleDate)

        //then
        Assert.assertEquals(respose, validResponseShiftsData)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun read_shouldThrowException_whenGetShiftsThrowsException() =
        mainCoroutineRule.runBlockingTest {

            //given
            coEvery { mediaSetService.getShifts(sampleDate) } throws sampleError
            try {
                //when
                dataSource.read(sampleDate)
            } catch (e: Exception) {
                //then
                Assert.assertEquals(e.javaClass, sampleError.javaClass)
                Assert.assertEquals(e.message, sampleError.message)
            }
        }
}