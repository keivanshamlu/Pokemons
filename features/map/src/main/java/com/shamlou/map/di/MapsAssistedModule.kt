package com.shamlou.map.di

import androidx.lifecycle.ViewModel
import com.shamlou.core.assisted.AssistedSavedStateViewModelFactory
import com.shamlou.core.anots.ViewModelKey
import com.shamlou.map.ui.MapsViewModel
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@AssistedModule
@Module(includes = [AssistedInject_MapsAssistedModule::class])
abstract class MapsAssistedModule {

    @Binds
    @IntoMap
    @MapsScope
    @ViewModelKey(MapsViewModel::class)
    abstract fun bindVMFactory(f: MapsViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>
}