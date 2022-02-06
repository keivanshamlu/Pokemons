package com.shamlou.data.repoImpl

import com.shamlou.bases.mapper.Mapper
import com.shamlou.bases.readWrite.Readable
import com.shamlou.data.model.shifts.ResponseShiftsData
import com.shamlou.data.read_write.shifts.RemoteReadable
import com.shamlou.domain.repo.ShiftsRepository
import com.shamlou.domain.model.shifts.ResponseShiftsDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class ShiftsRepositoryImpl @Inject constructor(
    @RemoteReadable private val shiftsRemoteReadable : Readable<String, ResponseShiftsData>,
    private val mapperShiftDataToDomain : Mapper<ResponseShiftsData, ResponseShiftsDomain>
) : ShiftsRepository {

    override fun getShifts(param: String): Flow<ResponseShiftsDomain> {

        return flow {
            shiftsRemoteReadable.read(param).also {
                emit(mapperShiftDataToDomain.map(it))
            }
        }
    }
}