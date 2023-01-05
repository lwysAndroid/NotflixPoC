package com.hcl.notflixpoc.core.network

import com.hcl.notflixpoc.core.network.model.CharacterSWContainer

interface StarWarsNetworkDataSource {
    suspend fun getCharactersSW(): CharacterSWContainer
}