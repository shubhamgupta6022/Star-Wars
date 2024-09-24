package com.sgupta.cleartrip.domain.model

data class StarWarMatchesDomainModel(
    val match: Int,
    val player1: PlayerDomainModel,
    val player2: PlayerDomainModel
)