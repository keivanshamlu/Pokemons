package com.shamlou.shift.ui.shifts

import androidx.lifecycle.SavedStateHandle
import com.shamlou.bases.mapper.Mapper
import com.shamlou.bases.useCase.FlowUseCase
import com.shamlou.bases.useCase.Resource
import com.shamlou.bases.useCase.map
import com.shamlou.domain.model.shifts.ResponseShiftsDomain
import com.shamlou.navigation.command.NavigationCommand
import com.shamlou.navigation.command.NavigationFlow
import com.shamlou.navigation.model.NavModelMap
import com.shamlou.shift.MainCoroutineRule
import com.shamlou.shift.ValidGetShiftsResponse.validResponseNavModel
import com.shamlou.shift.ValidGetShiftsResponse.validResponseShiftsDomain
import com.shamlou.shift.ValidGetShiftsResponse.validResponseShiftsView
import com.shamlou.shift.fakers.FakeUseCaseFailure
import com.shamlou.shift.fakers.FakeUseCaseSuccess
import com.shamlou.shift.getLastEmitted
import com.shamlou.shift.model.shifts.ResponseShiftDataView
import com.shamlou.shift.model.shifts.ResponseShiftsView
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

const val RESOURCES_PACKAGE = "com.shamlou.bases.useCase.ResourceKt"

class ShiftsViewModelUnitTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ShiftsViewModel

    @MockK
    lateinit var savedStateHandle: SavedStateHandle

    @MockK
    lateinit var useCaseShifts: FlowUseCase<Unit, ResponseShiftsDomain>

    @MockK(relaxed = true)
    lateinit var mapperShiftsDomainToView: Mapper<ResponseShiftsDomain, ResponseShiftsView>

    @MockK(relaxed = true)
    lateinit var mapperShiftsViewToNavModel: Mapper<ResponseShiftDataView, NavModelMap>

    //fakes
    private var getShiftsUseCaseSuccess = FakeUseCaseSuccess()
    private var getShiftsUseCaseFailure = FakeUseCaseFailure()

    @Before
    fun setUp() {

        MockKAnnotations.init(this)
        viewModel = ShiftsViewModel(
            savedStateHandle,
            getShiftsUseCaseFailure,
            mapperShiftsDomainToView,
            mapperShiftsViewToNavModel
        )
    }


    private fun stubValid() {
        // mockk way to stub Extension functions
        // (stub map and mapListed function of resource)
        mockkStatic(RESOURCES_PACKAGE)//mockk way to stub Extension functions
        every { mapperShiftsDomainToView.map(validResponseShiftsDomain) } returns validResponseShiftsView
        every { mapperShiftsViewToNavModel.map(validResponseShiftsView.data.first()) } returns validResponseNavModel
        every {
            Resource(Resource.Status.SUCCESS, validResponseShiftsDomain, null).map(
                mapperShiftsDomainToView
            )
        } returns Resource.success(validResponseShiftsView)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun getShifts_shouldBeCalledSuccess_whenUseCaseGetSuccessful() =
        mainCoroutineRule.dispatcher.runBlockingTest {

            //given
            stubValid()

            //when
            //fill viewModel with Faker that returns success
            viewModel = ShiftsViewModel(
                savedStateHandle,
                getShiftsUseCaseSuccess,
                mapperShiftsDomainToView,
                mapperShiftsViewToNavModel
            )

            //then
            val emitted = getLastEmitted(viewModel.shifts)
            Assert.assertEquals(emitted.status, Resource.Status.SUCCESS)
        }

    @Test
    @ExperimentalCoroutinesApi
    fun getShifts_shouldBeCalledError_whenUseCaseGetError() =
        mainCoroutineRule.dispatcher.runBlockingTest {

            //given
            stubValid()

            //when
            //fill viewModel with Faker that returns failure
            viewModel = ShiftsViewModel(
                savedStateHandle,
                getShiftsUseCaseFailure,
                mapperShiftsDomainToView,
                mapperShiftsViewToNavModel
            )

            //then
            val emitted = getLastEmitted(viewModel.shifts)
            Assert.assertEquals(emitted.status, Resource.Status.ERROR)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun clickItem_shouldNavigateUserToMap() = mainCoroutineRule.runBlockingTest {

        //given
        stubValid()
        //when
        viewModel.itemClicked(validResponseShiftsView.data.first())
        //then
        val emitted = getLastEmitted(viewModel.navigationCommand)
        Assert.assertEquals(
            NavigationCommand.To(NavigationFlow.ToMap(validResponseNavModel)),
            emitted?.peekContent()
        )
    }
    @ExperimentalCoroutinesApi
    @Test
    fun clickLogin_shouldNavigateUserToLogin() = mainCoroutineRule.runBlockingTest {

        //given
        stubValid()
        //when
        viewModel.loginClicked()
        //then
        val emitted = getLastEmitted(viewModel.toLogin)
        Assert.assertEquals(
            Unit,
            emitted.peekContent()
        )
    }
    @ExperimentalCoroutinesApi
    @Test
    fun clickSignUp_shouldNavigateUserToSignUp() = mainCoroutineRule.runBlockingTest {

        //given
        stubValid()
        //when
        viewModel.signUpClicked()
        //then
        val emitted = getLastEmitted(viewModel.toSignUp)
        Assert.assertEquals(
            Unit,
            emitted.peekContent()
        )
    }

}