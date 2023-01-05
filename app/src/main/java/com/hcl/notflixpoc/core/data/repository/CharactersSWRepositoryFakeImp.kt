package com.hcl.notflixpoc.core.data.repository

import com.hcl.notflixpoc.core.data.model.CharacterSWDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersSWRepositoryFakeImp @Inject constructor() : CharactersSWRepository {

    override fun getCharactersSW(): Flow<List<CharacterSWDataModel>> {
        val fakeList = listOf(
            CharacterSWDataModel(name = "One Film", stars = 4.2f),
            CharacterSWDataModel(name = "Two Film", stars = 4.7f),
        )
        return flow {
            emit(fakeList)
        }
    }

}