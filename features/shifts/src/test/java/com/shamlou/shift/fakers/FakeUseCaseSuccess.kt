package com.shamlou.shift.fakers

import com.shamlou.bases.useCase.FlowUseCase
import com.shamlou.bases.useCase.Resource
import com.shamlou.domain.model.shifts.ResponseShiftsDomain
import com.shamlou.shift.ValidGetShiftsResponse.sampleError
import com.shamlou.shift.ValidGetShiftsResponse.validResponseShiftsDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

// since mockk does not support stubbing for base classes,
// i created these 2 fake classes so i can stub invoke()
class FakeUseCaseSuccess : FlowUseCase<Unit, ResponseShiftsDomain> {

    override fun execute(parameters: Unit): Flow<Resource<ResponseShiftsDomain>> =
        flow { emit(Resource.success(validResponseShiftsDomain)) }

    override fun invoke(parameters: Unit): Flow<Resource<ResponseShiftsDomain>> =
        flow { emit(Resource.success(validResponseShiftsDomain)) }

}

class FakeUseCaseFailure : FlowUseCase<Unit, ResponseShiftsDomain> {
    override fun execute(parameters: Unit): Flow<Resource<ResponseShiftsDomain>> =
        flow { emit(Resource.error(sampleError)) }

    override fun invoke(parameters: Unit): Flow<Resource<ResponseShiftsDomain>> =
        flow { emit(Resource.error(sampleError)) }

}