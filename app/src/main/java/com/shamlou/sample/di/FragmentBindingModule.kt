package com.shamlou.sample.di

import com.shamlou.map.di.MapsAssistedModule
import com.shamlou.map.di.MapsModule
import com.shamlou.map.di.MapsScope
import com.shamlou.map.ui.FragmentMap
import com.shamlou.shift.di.ShiftsAssistedModule
import com.shamlou.shift.di.ShiftsScope
import com.shamlou.shift.ui.shifts.FragmentShifts
import com.shamlou.sample.di.features.ShiftsMappersModule
import com.shamlou.sample.di.features.ShiftsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentBindingModule {

    @ShiftsScope
    @ContributesAndroidInjector(
        modules = [
            ShiftsAssistedModule::class,
            ShiftsModule::class,
            ShiftsMappersModule::class]
    )
    internal abstract fun bindShifts(): FragmentShifts

    @MapsScope
    @ContributesAndroidInjector(
        modules = [MapsAssistedModule::class , MapsModule::class]
    )
    internal abstract fun bindMaps(): FragmentMap

}