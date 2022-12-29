package com.hcl.notflixpoc.core.network.di

import com.hcl.notflixpoc.core.network.NotflixPoCNetworkDataSource
import com.hcl.notflixpoc.core.network.retrofit.RetrofitNotflixPoCNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FlavoredNetworkModule {

    @Binds
    fun RetrofitNotflixPoCNetwork.binds(): NotflixPoCNetworkDataSource
}