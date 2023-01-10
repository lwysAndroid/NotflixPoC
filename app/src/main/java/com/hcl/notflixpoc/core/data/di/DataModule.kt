package com.hcl.notflixpoc.core.data.di

import com.hcl.notflixpoc.core.data.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

     /*@Binds
     fun bindsCharactersSWRepository(
         charactersSWRepository: CharactersSWRepositoryFakeImp
     ): CharactersSWRepository*/

    @Binds
    fun bindsCharactersSWRepository(
        charactersSWRepository: CharactersSWRepositoryImpl
    ): CharactersSWRepository

    @Binds
    fun bindsMoviesRepository(
        moviesRepository: MoviesRepositoryImpl
    ): MoviesRepository

    @Binds
    fun bindsMovieDetailsRepository(
        movieDetailsRepository: MovieDetailsRepositoryImpl
    ): MovieDetailsRepository

}