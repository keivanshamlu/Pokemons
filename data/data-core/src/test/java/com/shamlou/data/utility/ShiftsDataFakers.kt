package com.shamlou.data.utility

import com.shamlou.data.model.shifts.*
import com.shamlou.domain.model.shifts.*
import java.io.IOException


object ValidGetShiftsResponse{

    private val aggregationsDomain = ResponseAggregationsDomain(12)
    private val aggregationsData = ResponseAggregationsData(12)
    private val listDomain = listOf(
        ResponseShiftDataDomain("1234" , ResponseJobDomain("4321" , "title 1" , "", 0.0 , 0.0), ResponseRecurringShiftScheduleDomain("3421" , "16:30:00" , "23:30:00") , ResponseAverageEstimatedEarningsPerHourDomain("EUR" , 12000f)),
        ResponseShiftDataDomain("2354" , ResponseJobDomain("2335" , "title 2", "", 0.0 , 0.0), ResponseRecurringShiftScheduleDomain("3645" , "1:30:00" , "5:30:00") , ResponseAverageEstimatedEarningsPerHourDomain("EUR" , 54000f))
    )
    private val listData = listOf(
        ResponseShiftDataData("1234" , ResponseJobData("4321" , "title 1", "", 0.0 , 0.0), ResponseRecurringShiftScheduleData("3421" , "16:30:00" , "23:30:00") , ResponseAverageEstimatedEarningsPerHourData("EUR" , 12000f)),
        ResponseShiftDataData("2354" , ResponseJobData("2335" , "title 2", "", 0.0 , 0.0), ResponseRecurringShiftScheduleData("3645" , "1:30:00" , "5:30:00") , ResponseAverageEstimatedEarningsPerHourData("EUR" , 54000f))
    )

    val validResponseShiftsDomain = ResponseShiftsDomain(listDomain , aggregationsDomain)
    val validResponseShiftsData = ResponseShiftsData(listData , aggregationsData)
    const val sampleDate = "2022-02-05"
    private const val sampleErrorText = "this is an error"
    val sampleError = IOException(sampleErrorText)
}