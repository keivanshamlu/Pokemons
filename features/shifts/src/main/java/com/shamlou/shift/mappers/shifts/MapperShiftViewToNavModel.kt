package com.shamlou.shift.mappers.shifts

import com.shamlou.bases.mapper.Mapper
import com.shamlou.navigation.model.NavModelMap
import com.shamlou.shift.model.shifts.ResponseShiftDataView
import javax.inject.Inject


class MapperShiftViewToNavModel @Inject constructor() :
    Mapper<ResponseShiftDataView, NavModelMap> {
    override fun map(first: ResponseShiftDataView): NavModelMap =
        first.job.run { NavModelMap(lon, lat, title) }
}