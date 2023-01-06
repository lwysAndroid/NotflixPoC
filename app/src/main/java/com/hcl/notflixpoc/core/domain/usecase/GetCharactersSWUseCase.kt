package com.hcl.notflixpoc.core.domain.usecase

import com.hcl.notflixpoc.core.data.repository.CharactersSWRepository
import com.hcl.notflixpoc.core.domain.model.CharacterSWDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCharactersSWUseCase @Inject constructor(
    private val charactersSWRepository: CharactersSWRepository
) {

    operator fun invoke(): Flow<List<CharacterSWDomainModel>> {
        return charactersSWRepository.getCharactersSW().map { list ->
            list.map { movieDataModel ->
                CharacterSWDomainModel(
                    name = movieDataModel.name,
                    stars = movieDataModel.stars
                )
            }
        }
    }

}