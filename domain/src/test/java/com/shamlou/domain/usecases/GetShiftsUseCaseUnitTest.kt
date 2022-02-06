package com.shamlou.domain.usecases

import com.shamlou.bases.useCase.Resource
import com.shamlou.domain.repo.ShiftsRepository
import com.shamlou.domain.usecases.shifts.GetShiftsUseCase
import com.shamlou.domain.utility.MainCoroutineRule
import com.shamlou.domain.utility.ValidGetShiftsResponse.sampleDate
import com.shamlou.domain.utility.ValidGetShiftsResponse.sampleDateUnix
import com.shamlou.domain.utility.ValidGetShiftsResponse.sampleError
import com.shamlou.domain.utility.ValidGetShiftsResponse.validResponseShiftsDomain
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

class GetShiftsUseCaseUnitTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var useCase : GetShiftsUseCase

    @MockK
    lateinit var repository: ShiftsRepository
    private var date = Date()

    @Before
    fun setUp(){

        date.time = sampleDateUnix
        MockKAnnotations.init(this)
        useCase = GetShiftsUseCase(repository ,date)
    }
    @Test
    @ExperimentalCoroutinesApi
    fun execute_shouldCallGetAllShiftsAndReturnShiftsAsResources_whenSuccessful() {

        runBlocking {
            //given
            coEvery { repository.getShifts(sampleDate) } returns flow { emit(validResponseShiftsDomain) }
            //when
            val response = useCase.invoke(Unit).first()
            //then
            coVerify { repository.getShifts(sampleDate) }
            Assert.assertEquals(response, Resource.success(validResponseShiftsDomain))
        }
    }
    @Test
    @ExperimentalCoroutinesApi
    fun execute_shouldCallGetShiftsAndReturnErrorAsResources_whenErrorThrows() {

        runBlocking {
            //given
            coEvery { repository.getShifts(sampleDate) } throws sampleError
            //when
            val response = useCase.invoke(Unit).first()
            //then
            coVerify { repository.getShifts(sampleDate) }
            Assert.assertEquals(response, Resource.error(sampleError , null))
        }
    }
}