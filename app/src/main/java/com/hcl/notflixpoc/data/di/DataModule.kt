package com.hcl.notflixpoc.data.di

import com.hcl.notflixpoc.data.repository.PopularMoviesRepository
import com.hcl.notflixpoc.data.repository.PopularMoviesRepositoryFakeImp
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