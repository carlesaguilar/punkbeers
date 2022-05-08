package dev.carlesav.catalog_data.repository

import dev.carlesav.catalog_data.remote.PunkApi
import dev.carlesav.core.catalogListResponse
import dev.carlesav.core.util.Resource
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class PunkRepositoryImplTest {
    private lateinit var repository: PunkRepositoryImpl
    private lateinit var mockWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var api: PunkApi

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()

        okHttpClient = OkHttpClient.Builder()
            .writeTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.SECONDS)
            .build()

        api = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(PunkApi::class.java)

        repository = PunkRepositoryImpl(
            api = api
        )
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Search beers, show loading`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(catalogListResponse)
        )
        repository.getBeers(query = "", page = 1).take(1).collect { response ->
            Assert.assertTrue(response is Resource.Loading)
        }
    }

    @Test
    fun `Search beers, valid response, returns results`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(catalogListResponse)
        )
        repository.getBeers(query = "", page = 1).take(3).collect { response ->
            if (response is Resource.Success) {
                Assert.assertEquals(25, response.data?.size)
            }
        }
    }
}