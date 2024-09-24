package com.sgupta.cleartrip.domain.repo

import com.sgupta.cleartrip.domain.model.PlayerDomainModel
import com.sgupta.cleartrip.domain.model.StarWarMatchesDomainModel

interface StarWarsRepo {
    suspend fun getStarWarPlayers(): List<PlayerDomainModel>
    suspend fun getStarWarMatches(): List<StarWarMatchesDomainModel>
}