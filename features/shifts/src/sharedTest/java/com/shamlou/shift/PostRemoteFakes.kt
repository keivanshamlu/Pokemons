package com.shamlou.shift

import com.shamlou.domain.model.shifts.*
import com.shamlou.navigation.model.NavModelMap
import com.shamlou.shift.model.shifts.*
import java.io.IOException


object ValidGetShiftsResponse{

    private val aggregationsDomain = ResponseAggregationsDomain(12)
    private val listDomain = listOf(
        ResponseShiftDataDomain("1234" , ResponseJobDomain("4321" , "title 1","", 0.0, 0.0), ResponseRecurringShiftScheduleDomain("3421" , "16:30:00" , "23:30:00") , ResponseAverageEstimatedEarningsPerHourDomain("EUR" , 12000f)),
        ResponseShiftDataDomain("2354" , ResponseJobDomain("2335" , "title 2","", 0.0, 0.0), ResponseRecurringShiftScheduleDomain("3645" , "1:30:00" , "5:30:00") , ResponseAverageEstimatedEarningsPerHourDomain("EUR" , 54000f))
    )
    private val listView = listOf(
        ResponseShiftDataView("1234" , ResponseJobView("4321" , "title 1","", 0.0, 0.0), ResponseRecurringShiftScheduleView("3421" , "16:30 - 23:30") , "12000 EUR"),
        ResponseShiftDataView("2354" , ResponseJobView("2335" , "title 2","", 0.0, 0.0), ResponseRecurringShiftScheduleView("3645" , "1:30 - 5:30") , "54000 EUR")
    )

    val validResponseShiftsDomain = ResponseShiftsDomain(listDomain , aggregationsDomain)
    val validResponseShiftsView = ResponseShiftsView(listView , "12 results")
    val validResponseNavModel = NavModelMap(0.0, 0.0 , "title 1")
    private const val sampleErrorText = "this is an error"
    val sampleError = IOException(sampleErrorText)
}