package com.shamlou.domain.repo

import com.shamlou.domain.model.shifts.ResponseShiftsDomain
import kotlinx.coroutines.flow.Flow

interface ShiftsRepository {

    fun getShifts(param: String): Flow<ResponseShiftsDomain>
}