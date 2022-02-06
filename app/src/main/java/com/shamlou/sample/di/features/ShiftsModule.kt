package com.shamlou.sample.di.features

import com.shamlou.bases.readWrite.Readable
import com.shamlou.bases.useCase.FlowUseCase
import com.shamlou.core.assisted.InjectingSavedStateViewModelFactory
import com.shamlou.core.assisted.ViewModelFactory
import com.shamlou.data.model.shifts.ResponseShiftsData
import com.shamlou.data.repoImpl.ShiftsRepositoryImpl
import com.shamlou.data.read_write.shifts.RemoteReadable
import com.shamlou.data_remote.services.shifts.ShiftsService
import com.shamlou.data_remote.dataSources.RemoteShiftsDataSource
import com.shamlou.domain.model.shifts.ResponseShiftsDomain
import com.shamlou.domain.repo.ShiftsRepository
import com.shamlou.domain.usecases.shifts.GetShiftsUseCase
import com.shamlou.shift.di.ShiftsScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import java.util.*

@Module
abstract class ShiftsModule {

    @Binds
    @RemoteReadable
    @ShiftsScope
    abstract fun bindShiftsRemoteReadable(shiftsRemoteReadable : RemoteShiftsDataSource) : Readable<String, ResponseShiftsData>

    @Binds
    @ShiftsScope
    abstract fun bindShiftsRepository(repo : ShiftsRepositoryImpl) : ShiftsRepository

    @Binds
    @ShiftsScope
    abstract fun bindShiftsUseCase(useCase : GetShiftsUseCase): FlowUseCase<Unit, ResponseShiftsDomain>

    @Binds
    @ShiftsScope
    abstract fun bindViewModelFactory(mapper: InjectingSavedStateViewModelFactory): ViewModelFactory

    companion object {

        @Provides
        @ShiftsScope
        fun provideShiftsService(retrofit : Retrofit): ShiftsService {
            return retrofit.create(ShiftsService::class.java)
        }
        @Provides
        @ShiftsScope
        fun provideDate(): Date {
            return Date()
        }
    }
}