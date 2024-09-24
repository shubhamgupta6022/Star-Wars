package com.sgupta.cleartrip.domain.usecase

import com.sgupta.cleartrip.domain.model.PlayerDomainModel
import com.sgupta.cleartrip.domain.repo.StarWarsRepo
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow
import javax.inject.Inject

class GetPlayersUseCase @Inject constructor(
    private val repo: StarWarsRepo
) {
    suspend operator fun invoke(): List<PlayerDomainModel> = repo.getStarWarPlayers()
}