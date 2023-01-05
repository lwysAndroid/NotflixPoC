package com.hcl.notflixpoc.core.data.repository

import com.hcl.notflixpoc.core.data.model.CharacterSWDataModel
import kotlinx.coroutines.flow.Flow

interface CharactersSWRepository {
    fun getCharactersSW(): Flow<List<CharacterSWDataModel>>
}