package com.hcl.notflixpoc.core.network.retrofit

import com.hcl.notflixpoc.core.network.StarWarsNetworkDataSource
import com.hcl.notflixpoc.core.network.model.CharacterSWContainer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

private interface RetrofitStarWarsNetworkApi {
    @GET(value = "people/")
    suspend fun getPeopleSW(): CharacterSWContainer
}

private const val StarWarsBaseUrl = "https://swapi.dev/api/"


@Singleton
class RetrofitStarWarsNetwork @Inject constructor() : StarWarsNetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(StarWarsBaseUrl)
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
        .create(RetrofitStarWarsNetworkApi::class.java)

    override suspend fun getCharactersSW(): CharacterSWContainer =
        networkApi.getPeopleSW()
}