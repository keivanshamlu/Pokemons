package com.shamlou.data.mappers.shifts

import com.shamlou.bases.mapper.Mapper
import com.shamlou.data.model.shifts.*
import com.shamlou.domain.model.shifts.*
import javax.inject.Inject

class MapperShiftDataToDomain @Inject constructor() :
    Mapper<ResponseShiftsData, ResponseShiftsDomain> {
    override fun map(first: ResponseShiftsData): ResponseShiftsDomain = first.run {
        ResponseShiftsDomain(
            data.run {
                data.map {
                    ResponseShiftDataDomain(
                        it.id,
                        it.job.run {
                            ResponseJobDomain(
                                id,
                                title,
                                extraBriefing,
                                lat,
                                lon
                            )
                        },
                        it.recurringShiftSchedule.run {
                            ResponseRecurringShiftScheduleDomain(
                                id,
                                startsAt,
                                endsAt
                            )
                        },
                        it.averageEstimatedEarningsPerHour.run {
                            ResponseAverageEstimatedEarningsPerHourDomain(
                                currency,
                                amount
                            )
                        }
                    )
                }
            },
            aggregations.run {
                ResponseAggregationsDomain(
                    count
                )
            }
        )
    }
}