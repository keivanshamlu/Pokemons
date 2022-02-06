package com.shamlou.domain.usecases.shifts

import com.shamlou.bases.useCase.FlowUseCase
import com.shamlou.bases.useCase.Resource
import com.shamlou.domain.model.shifts.ResponseShiftsDomain
import com.shamlou.domain.repo.ShiftsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * gets shifts from repo and map it to [Resource],
 * in the [FlowUseCase] loading will be set at the
 * beginning of the execution
 */
class GetShiftsUseCase @Inject constructor(
    private val repository: ShiftsRepository,
    private val date: Date
) : FlowUseCase<Unit, ResponseShiftsDomain> {

    override fun execute(parameters: Unit): Flow<Resource<ResponseShiftsDomain>> {
        return repository.getShifts(getDateTime()).map {
            Resource.success(it)
        }
    }

    private fun getDateTime(): String = SimpleDateFormat("yyyy-MM-dd").run {
        return format(date)
    }
}