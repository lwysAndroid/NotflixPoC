package com.hcl.notflixpoc.core.data.di

import com.hcl.notflixpoc.core.data.repository.PopularMoviesRepository
import com.hcl.notflixpoc.core.data.repository.PopularMoviesRepositoryFakeImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsPopularMoviesRepository(
        popularMoviesRepository: PopularMoviesRepositoryFakeImp
    ): PopularMoviesRepository

}