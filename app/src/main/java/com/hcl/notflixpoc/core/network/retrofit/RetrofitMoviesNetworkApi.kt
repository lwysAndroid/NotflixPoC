package com.hcl.notflixpoc.core.network.retrofit

import com.hcl.notflixpoc.core.network.model.NetworkMovieResult
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

private interface RetrofitMoviesNetworkApi {
    @GET(value = "trending/movie/week")
    suspend fun getTrendingMovies(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = "423f0418a6d6586755fe3d7227327ef2",
        @Query("language") language: String = "en",
    ): NetworkMovieResult
}

private const val MoviesBaseUrl = "https://api.themoviedb.org/3/"

@Singleton
class RetrofitMoviesNetwork @Inject constructor() : MoviesNetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(MoviesBaseUrl)
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
        .create(RetrofitMoviesNetworkApi::class.java)

    override suspend fun getTrendingMovies(
        page: Int,
        apiKey: String,
        language: String,
    ): NetworkMovieResult =
        networkApi.getTrendingMovies(page = page, apiKey = apiKey, language = language)

}