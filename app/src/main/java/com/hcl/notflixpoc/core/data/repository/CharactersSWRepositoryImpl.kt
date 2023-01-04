package com.hcl.notflixpoc.core.data.repository

import com.hcl.notflixpoc.core.data.model.CharacterSWDataModel
import com.hcl.notflixpoc.core.network.StarWarsNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersSWRepositoryImpl @Inject constructor(
    private val network: StarWarsNetworkDataSource
) : CharactersSWRepository {

    override fun getCharactersSW(): Flow<List<CharacterSWDataModel>> {
        return flow {
            network.getCharactersSW().results.map {
                CharacterSWDataModel(name = it.name, stars = 4.7f)
            }.also { emit(it) }
        }
    }
}