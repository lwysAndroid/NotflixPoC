package com.hcl.notflixpoc.core.network.retrofit

import com.hcl.notflixpoc.core.network.NotflixPoCNetworkDataSource
import com.hcl.notflixpoc.core.network.model.PeopleSWContainer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

private interface RetrofitNotflixPoCNetworkApi {
    @GET(value = "people/")
    suspend fun getPeopleSW(): PeopleSWContainer
}

private const val NotflixPoCBaseUrl = "https://swapi.dev/api/"


@Singleton
class RetrofitNotflixPoCNetwork @Inject constructor() : NotflixPoCNetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(NotflixPoCBaseUrl)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    // TODO: Decide logging logic
                    HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                )
                .build()
        )

        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetrofitNotflixPoCNetworkApi::class.java)

    override suspend fun getPeopleSW(): PeopleSWContainer =
        networkApi.getPeopleSW()
}