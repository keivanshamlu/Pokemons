package com.shamlou.data_remote.utility

import com.shamlou.data.model.shifts.*
import com.shamlou.data_remote.model.shifts.*
import java.io.IOException

/**
 * data provided here are corresponding to each other
 */
object ValidGetShiftsResponse {

    private val aggregationsRemote = ResponseAggregationsRemote(12)
    private val aggregationsData = ResponseAggregationsData(12)
    private val aggregationsDataEmpty = ResponseAggregationsData(0)
    private val listRemote = listOf(
        ResponseShiftDataRemote(
            "1234",
            ResponseJobRemote("4321", "title 1"),
            ResponseRecurringShiftScheduleRemote("3421", "16:30:00", "23:30:00"),
            ResponseAverageEstimatedEarningsPerHourRemote("EUR", 12000f)
        ),
        ResponseShiftDataRemote(
            "2354",
            ResponseJobRemote("2335", "title 2"),
            ResponseRecurringShiftScheduleRemote("3645", "1:30:00", "5:30:00"),
            ResponseAverageEstimatedEarningsPerHourRemote("EUR", 54000f)
        )
    )
    private val listData = listOf(
        ResponseShiftDataData(
            "1234",
            ResponseJobData("4321", "title 1", "", 0.0, 0.0),
            ResponseRecurringShiftScheduleData("3421", "16:30:00", "23:30:00"),
            ResponseAverageEstimatedEarningsPerHourData("EUR", 12000f)
        ),
        ResponseShiftDataData(
            "2354",
            ResponseJobData("2335", "title 2", "", 0.0, 0.0),
            ResponseRecurringShiftScheduleData("3645", "1:30:00", "5:30:00"),
            ResponseAverageEstimatedEarningsPerHourData("EUR", 54000f)
        )
    )

    val validGetShiftsResponse = """
          {
  "data": [
    {
      "id": 1234,
      "job": {
        "id": 4321,
        "title": "title 1"
      },
      "recurring_shift_schedule": {
        "id": 3421,
        "starts_at": "16:30:00",
        "ends_at": "23:30:00"
      },
      "average_estimated_earnings_per_hour": {
        "currency": "EUR",
        "amount": 12000
      }
    },
    {
      "id": 2354,
      "job": {
        "id": 2335,
        "title": "title 2"
      },
      "recurring_shift_schedule": {
        "id": 3645,
        "starts_at": "1:30:00",
        "ends_at": "5:30:00"
      },
      "average_estimated_earnings_per_hour": {
        "currency": "EUR",
        "amount": 54000
      }
    }
  ],
  "aggregations": {
    "count": 12
  }
}
    """.trimIndent()

    val validResponseShiftsRemote = ResponseShiftsRemote(listRemote, aggregationsRemote)
    val validResponseShiftsRemoteNull = ResponseShiftsRemote(null, null)
    val validResponseShiftsData = ResponseShiftsData(listData, aggregationsData)
    val validResponseShiftsEmpty = ResponseShiftsData(listOf(), aggregationsDataEmpty)
    const val sampleDate = "2022-02-05"
    private const val sampleErrorText = "this is an error"
    val sampleError = IOException(sampleErrorText)
}

