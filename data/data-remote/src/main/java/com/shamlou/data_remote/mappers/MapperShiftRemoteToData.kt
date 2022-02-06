package com.shamlou.data_remote.mappers

import com.shamlou.bases.mapper.Mapper
import com.shamlou.data.model.shifts.*
import com.shamlou.data_remote.model.shifts.ResponseShiftsRemote
import javax.inject.Inject


/**
 * maps shift data from remote to data
 */
class MapperShiftRemoteToData @Inject constructor() :
    Mapper<ResponseShiftsRemote, ResponseShiftsData> {
    override fun map(first: ResponseShiftsRemote): ResponseShiftsData = first.run {
        ResponseShiftsData(
            data.run {
                data?.map {
                    ResponseShiftDataData(
                        it.id ?: "",
                        it.job.let {
                            ResponseJobData(
                                it?.id ?: "",
                                it?.title ?: "",
                                it?.extraBriefing ?: "",
                                it?.reportAtAddress?.geo?.lat ?: 0.0,
                                it?.reportAtAddress?.geo?.lon ?: 0.0
                            )
                        },
                        it.recurringShiftSchedule.let {
                            ResponseRecurringShiftScheduleData(
                                it?.id ?: "",
                                it?.startsAt ?: "",
                                it?.endsAt ?: ""
                            )
                        },
                        it.averageEstimatedEarningsPerHour.let {
                            ResponseAverageEstimatedEarningsPerHourData(
                                it?.currency ?: "",
                                it?.amount ?: 0f
                            )
                        }
                    )
                } ?: listOf()
            },
            aggregations.let {
                ResponseAggregationsData(
                    it?.count ?: 0
                )
            }
        )
    }
}