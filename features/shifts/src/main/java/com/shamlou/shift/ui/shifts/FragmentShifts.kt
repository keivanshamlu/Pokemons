package com.shamlou.shift.ui.shifts

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.shamlou.bases_android.fragment.BaseFragment
import com.shamlou.core.assisted.ViewModelFactory
import com.shamlou.shift.R
import com.shamlou.shift.databinding.FragmentShiftsBinding
import com.shamlou.shift.ui.LoginActivity
import com.shamlou.shift.ui.SignUpActivity
import com.shamlou.shift.ui.shifts.shifts.AdapterShifts
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class FragmentShifts : BaseFragment<ShiftsViewModel, FragmentShiftsBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val adapterShifts = AdapterShifts()
    override val layoutRes: Int = R.layout.fragment_shifts

    override val viewModel by viewModels<ShiftsViewModel> {
        viewModelFactory.create(this, arguments)
    }
    override fun hookVariables() {
        binding?.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        observeViewModel()
    }

    /**
     * since i don't have much time, i didn't create
     * fragments for login and sign up (like i did for map)
     * and i just create 2 activities.
     */
    private fun observeViewModel(){

        // i use repeatOnLifecycle to prevent bugs
        // that may accrue when app is in background
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.toLogin.collect {
                    it.getContentIfNotHandled()?.let {
                        startActivity(Intent(requireActivity() , LoginActivity::class.java))
                    }
                }
            }
        }
        // i use repeatOnLifecycle to prevent bugs
        // that may accrue when app is in background
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.toSignUp.collect {
                    it.getContentIfNotHandled()?.let {
                        startActivity(Intent(requireActivity() , SignUpActivity::class.java))
                    }
                }
            }
        }
    }

    /**
     * init recyclerView adapter and layout manager
     * and connect viewModel in order to pass onclick
     * event
     */
    private fun setUpRecyclerView(){

        binding?.recyclerviewShifts?.apply {
            adapter = adapterShifts.apply {
                onItemClicked = { clickedItem , _ ->

                    viewModel.itemClicked(clickedItem)
                }
            }
            layoutManager = LinearLayoutManager(context)
        }
    }
}