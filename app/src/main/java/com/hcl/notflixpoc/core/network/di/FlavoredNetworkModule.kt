package com.hcl.notflixpoc.core.network.di

import com.hcl.notflixpoc.core.network.StarWarsNetworkDataSource
import com.hcl.notflixpoc.core.network.retrofit.RetrofitStarWarsNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FlavoredNetworkModule {

    @Binds
    fun RetrofitStarWarsNetwork.binds(): StarWarsNetworkDataSource
}