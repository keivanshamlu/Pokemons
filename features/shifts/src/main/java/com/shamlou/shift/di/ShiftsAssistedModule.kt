package com.shamlou.shift.di

import androidx.lifecycle.ViewModel
import com.shamlou.core.assisted.AssistedSavedStateViewModelFactory
import com.shamlou.core.anots.ViewModelKey
import com.shamlou.shift.ui.shifts.ShiftsViewModel
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@AssistedModule
@Module(includes = [AssistedInject_ShiftsAssistedModule::class])
abstract class ShiftsAssistedModule {

    @Binds
    @IntoMap
    @ShiftsScope
    @ViewModelKey(ShiftsViewModel::class)
    abstract fun bindVMFactory(f: ShiftsViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>
}