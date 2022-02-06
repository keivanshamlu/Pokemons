package com.shamlou.shift.mappers.shifts

import com.shamlou.bases.mapper.Mapper
import com.shamlou.domain.model.shifts.ResponseShiftsDomain
import com.shamlou.shift.model.shifts.*
import javax.inject.Inject


class MapperShiftDomainToView @Inject constructor() :
    Mapper<ResponseShiftsDomain, ResponseShiftsView> {
    override fun map(first: ResponseShiftsDomain): ResponseShiftsView = first.run {
        ResponseShiftsView(
            data.run {
                data.map {
                    ResponseShiftDataView(
                        it.id,
                        it.job.run {
                            ResponseJobView(
                                id,
                                title,
                                extraBriefing,
                                lat,
                                lon
                            )
                        },
                        it.recurringShiftSchedule.run {
                            ResponseRecurringShiftScheduleView(
                                id,
                                if (startsAt.isNotEmpty() && endsAt.isNotEmpty())
                                    "${startsAt.dropLast(3)} - ${endsAt.dropLast(3)}"
                                else
                                    ""
                            )
                        },
                        it.averageEstimatedEarningsPerHour.run {
                            "$amount $currency"
                        }
                    )
                }
            },
            aggregations.run {
                "$count results"
            }
        )
    }
}