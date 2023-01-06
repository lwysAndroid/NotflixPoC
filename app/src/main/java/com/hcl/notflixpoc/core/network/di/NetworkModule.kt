package com.hcl.notflixpoc.core.network.di

import com.hcl.notflixpoc.core.network.retrofit.MoviesNetworkDataSource
import com.hcl.notflixpoc.core.network.retrofit.RetrofitMoviesNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    fun bindsMoviesNetworkDataSource(
        moviesNetworkDataSource: RetrofitMoviesNetwork
    ): MoviesNetworkDataSource
    
}