package com.sgupta.cleartrip.domain.usecase

import com.sgupta.cleartrip.domain.model.StarWarMatchesDomainModel
import com.sgupta.cleartrip.domain.repo.StarWarsRepo
import javax.inject.Inject

class GetMatchesUseCase @Inject constructor(private val repo: StarWarsRepo) {
    suspend operator fun invoke(): List<StarWarMatchesDomainModel> {
        return repo.getStarWarMatches()
    }
}