package com.shamlou.map.ui

import androidx.lifecycle.SavedStateHandle
import com.google.android.gms.maps.model.LatLng
import com.shamlou.bases_android.viewModel.BaseViewModel
import com.shamlou.core.assisted.AssistedSavedStateViewModelFactory
import com.shamlou.navigation.model.NavModelMap
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MapsViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle) : BaseViewModel() {

    private val _showLocationEvent = MutableSharedFlow<Pair<LatLng , String>>(replay = 1)
    val showLocationEvent: MutableSharedFlow<Pair<LatLng , String>>
        get() = _showLocationEvent


    private val _shift = MutableStateFlow<NavModelMap?>(null)
    val shift: StateFlow<NavModelMap?>
        get() = _shift

    fun setShift(shift : NavModelMap){
        _shift.tryEmit(shift)
    }

    /**
     * whenever map is ready to show a location, it will map selected loc to
     * LatLng and emit it on [_showLocationEvent] which is a event and this
     * event will be observed in Fragment to be shown in map
     */
    fun onMapReady() {

        shift.value?.apply {
            _showLocationEvent.tryEmit(Pair(LatLng(lat, lon) , title))
        }
    }
    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<MapsViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): MapsViewModel
    }
}