package com.shamlou.shift.ui.shifts

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.shamlou.bases.mapper.Mapper
import com.shamlou.bases.useCase.FlowUseCase
import com.shamlou.bases.useCase.Resource
import com.shamlou.bases.useCase.map
import com.shamlou.bases_android.event.Event
import com.shamlou.bases_android.viewModel.BaseViewModel
import com.shamlou.core.assisted.AssistedSavedStateViewModelFactory
import com.shamlou.domain.model.shifts.ResponseShiftsDomain
import com.shamlou.navigation.command.NavigationFlow
import com.shamlou.navigation.model.NavModelMap
import com.shamlou.shift.model.shifts.ResponseShiftDataView
import com.shamlou.shift.model.shifts.ResponseShiftsView
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ShiftsViewModel @AssistedInject constructor(@Assisted private val savedStateHandle: SavedStateHandle,
                                                  private val getShiftsUseCase : FlowUseCase<Unit, ResponseShiftsDomain>,
                                                  private val mapperShiftsDomainToView : Mapper<ResponseShiftsDomain, ResponseShiftsView>,
                                                  private val mapperShiftsViewToNavModel : Mapper<ResponseShiftDataView, NavModelMap>,
) : BaseViewModel() {

    //holds shifts and their status
    private val _shifts = MutableStateFlow<Resource<ResponseShiftsView>>(Resource.loading())
    val shifts: StateFlow<Resource<ResponseShiftsView>>
        get() = _shifts

    //an event for  navigating to login page
    private val _toLogin = MutableSharedFlow<Event<Unit>>(replay = 1)
    val toLogin: MutableSharedFlow<Event<Unit>>
        get() = _toLogin

    //an event for navigating to sign up
    private val _toSignUp = MutableSharedFlow<Event<Unit>>(replay = 1)
    val toSignUp: MutableSharedFlow<Event<Unit>>
        get() = _toSignUp

    init {

        getShiftsAsync()
    }

    /**
     * gets all items at the beginning and maps it to view objects
     * delegates error handling to base [handleNetworkError] in [BaseViewModel]
     */
    fun getShiftsAsync() {
        viewModelScope.launch {
            _shifts.emit(Resource.loading())
            getShiftsUseCase.invoke(Unit).map {
                it.map(mapperShiftsDomainToView)
            }.collect {
                handleNetworkError(it)
                _shifts.emit(it)
            }
        }
    }

    /**
     * calls when user click on login button
     */
    fun loginClicked(){

        _toLogin.tryEmit(Event(Unit))
    }
    /**
     * calls when user click on sign up button
     */
    fun signUpClicked(){

        _toSignUp.tryEmit(Event(Unit))
    }
    /**
     * calls when user click on a shift
     */
    fun itemClicked(clickedItem :ResponseShiftDataView){
        
        mapperShiftsViewToNavModel.map(clickedItem).also {

            navigateTo(NavigationFlow.ToMap(it))
        }
    }

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<ShiftsViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): ShiftsViewModel
    }
}