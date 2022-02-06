package com.shamlou.map.di

import com.shamlou.core.assisted.InjectingSavedStateViewModelFactory
import com.shamlou.core.assisted.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class MapsModule {

    @Binds
    @MapsScope
    abstract fun bindViewModelFactory(mapper: InjectingSavedStateViewModelFactory): ViewModelFactory
}