package com.shamlou.map.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.shamlou.bases_android.fragment.BaseFragment
import com.shamlou.map.R
import com.shamlou.map.databinding.FragmentMapBinding
import com.shamlou.core.assisted.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import com.google.android.gms.maps.model.CameraPosition
import javax.inject.Inject


class FragmentMap: BaseFragment<MapsViewModel, FragmentMapBinding>() , OnMapReadyCallback {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val args : FragmentMapArgs by navArgs()
    override val viewModel by viewModels<MapsViewModel> {
        viewModelFactory.create(this)
    }
    override val layoutRes: Int = R.layout.fragment_map
    private lateinit var mMap: GoogleMap
    override fun hookVariables() {
        binding?.viewModel = viewModel
        viewModel.setShift(args.arg)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpMap()
        observeViewModel()
    }

    private fun observeViewModel(){

        // i use repeatOnLifecycle to prevent bugs
        // that may accrue when app is in background
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.showLocationEvent.collect {

                    moveAndShowLocation(it)
                }
            }
        }
    }

    /**
     * sets up map
     */
    private fun setUpMap(){

        (childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment).apply {
            getMapAsync(this@FragmentMap)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        viewModel.onMapReady()
    }

    /**
     * adds marker and moves camera to given location
     */
    private fun moveAndShowLocation(loc : Pair<LatLng , String>){
        if(::mMap.isInitialized.not())return
        mMap.apply {

            //todo
            addMarker(MarkerOptions().position(loc.first).title(loc.second))
            moveCamera(CameraUpdateFactory.newLatLng(loc.first))
            val cameraPosition = CameraPosition.Builder()
                .target(loc.first)
                .zoom(10f).build()
            //Zoom in and animate the camera.
            animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        }
    }
}