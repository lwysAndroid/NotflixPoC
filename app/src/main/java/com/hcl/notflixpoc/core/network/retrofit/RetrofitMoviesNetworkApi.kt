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
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
    ): NetworkMovieResult

    @GET(value = "movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
    ): NetworkMovieResult

    @GET(value = "movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
    ): NetworkMovieResult
}

private const val MOVIES_BASE_URL = "https://api.themoviedb.org/3/"
private const val API_KEY: String = "423f0418a6d6586755fe3d7227327ef2"

@Singleton
class RetrofitMoviesNetwork @Inject constructor() : MoviesNetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(MOVIES_BASE_URL)
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

    override suspend fun getTrendingMovies(page: Int, language: String): NetworkMovieResult =
        networkApi.getTrendingMovies(page = page, apiKey = API_KEY, language = language)

    override suspend fun getUpcomingMovies(page: Int, language: String): NetworkMovieResult =
        networkApi.getUpcomingMovies(page = page, apiKey = API_KEY, language = language)

    override suspend fun getPopularMovies(page: Int, language: String): NetworkMovieResult =
        networkApi.getPopularMovies(page = page, apiKey = API_KEY, language = language)

}