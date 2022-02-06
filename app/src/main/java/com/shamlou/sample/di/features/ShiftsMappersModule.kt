package com.shamlou.sample.di.features

import com.shamlou.bases.mapper.Mapper
import com.shamlou.data.mappers.shifts.MapperShiftDataToDomain
import com.shamlou.data.model.shifts.ResponseShiftsData
import com.shamlou.data_remote.mappers.MapperShiftRemoteToData
import com.shamlou.data_remote.model.shifts.ResponseShiftsRemote
import com.shamlou.domain.model.shifts.ResponseShiftsDomain
import com.shamlou.navigation.model.NavModelMap
import com.shamlou.shift.di.ShiftsScope
import com.shamlou.shift.mappers.shifts.MapperShiftDomainToView
import com.shamlou.shift.mappers.shifts.MapperShiftViewToNavModel
import com.shamlou.shift.model.shifts.ResponseShiftDataView
import com.shamlou.shift.model.shifts.ResponseShiftsView
import dagger.Binds
import dagger.Module

/**
 * bind mappers to their corresponding base type
 */
@Module
abstract class ShiftsMappersModule {

    @Binds
    @ShiftsScope
    abstract fun bindMapperShiftsRemoteToData(mapper: MapperShiftRemoteToData): Mapper<ResponseShiftsRemote, ResponseShiftsData>

    @Binds
    @ShiftsScope
    abstract fun bindMapperShiftsDataToDomain(mapper: MapperShiftDataToDomain): Mapper<ResponseShiftsData, ResponseShiftsDomain>

    @Binds
    @ShiftsScope
    abstract fun bindMapperShiftsDomainToView(mapper: MapperShiftDomainToView): Mapper<ResponseShiftsDomain, ResponseShiftsView>

    @Binds
    @ShiftsScope
    abstract fun bindMapperShiftsViewToNavModel(mapper: MapperShiftViewToNavModel): Mapper<ResponseShiftDataView, NavModelMap>

}