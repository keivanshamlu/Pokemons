package com.shamlou.data_remote.dataSources

import com.shamlou.bases.mapper.Mapper
import com.shamlou.data.model.shifts.ResponseShiftsData
import com.shamlou.data_remote.model.shifts.ResponseShiftsRemote
import com.shamlou.data_remote.services.shifts.ShiftsService
import com.shamlou.data_remote.utility.MainCoroutineRule
import com.shamlou.data_remote.utility.ValidGetShiftsResponse.sampleDate
import com.shamlou.data_remote.utility.ValidGetShiftsResponse.validGetShiftsResponse
import com.shamlou.data_remote.utility.ValidGetShiftsResponse.validResponseShiftsData
import com.shamlou.data_remote.utility.ValidGetShiftsResponse.validResponseShiftsRemote
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import okhttp3.OkHttpClient
import org.junit.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.EOFException

/**
 * test how source code interacts with a real webserver
 * regression tests also provided, so there would be no
 * changes in URL and PARAMs
 */
class RemoteShiftsDataSourceIntegrationTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val mockWebServer = MockWebServer()

    private val service = Retrofit.Builder()
        .client(OkHttpClient.Builder().build())
        .baseUrl(mockWebServer.url("/"))
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ShiftsService::class.java)

    private lateinit var dataSource: RemoteShiftsDataSource

    @MockK
    lateinit var mapperRemoteToData: Mapper<ResponseShiftsRemote, ResponseShiftsData>

    @Before
    fun setUp() {

        MockKAnnotations.init(this)
        dataSource = RemoteShiftsDataSource(service, mapperRemoteToData)
        mockWebServer.start(8080)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun read_shouldGetValidDataWhenServerRespondSuccessfully() {
        //given
        mockWebServer.enqueue(
            MockResponse().setBody(validGetShiftsResponse).setResponseCode(200)
        )
        runBlocking {

            every { mapperRemoteToData.map(validResponseShiftsRemote) } returns validResponseShiftsData
            //when
            val response = dataSource.read(sampleDate)

            //then
            verify { mapperRemoteToData.map(validResponseShiftsRemote) }
            Assert.assertEquals(
                response,
                validResponseShiftsData
            )
        }
    }

    @Test
    @ExperimentalCoroutinesApi
    fun read_shouldThrowExceptionWhenServerRespondError() {
        //given
        mockWebServer.enqueue(
            MockResponse().setHttp2ErrorCode(404)
        )
        runBlocking {

            every { mapperRemoteToData.map(validResponseShiftsRemote) } returns validResponseShiftsData
            try {
                //when
                dataSource.read(sampleDate)
            } catch (e: Exception) {
                //then
                Assert.assertEquals(e.javaClass, EOFException::class.java)
            }
        }
    }

    /**
     * checks whether method or url is not changed accidentally through time
     */
    @Test
    @ExperimentalCoroutinesApi
    fun regressionTest() {
        //given
        mockWebServer.enqueue(
            MockResponse().setBody(validGetShiftsResponse).setResponseCode(200)
        )
        runBlocking {
            every { mapperRemoteToData.map(validResponseShiftsRemote) } returns validResponseShiftsData
            dataSource.read(sampleDate)
            Assert.assertEquals(mockWebServer.requestCount, 1)
            mockWebServer.takeRequest().apply {
                Assert.assertEquals(method, "GET")
                Assert.assertEquals(path, "/api/v3/shifts?filter[date]=$sampleDate")
            }
        }
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}