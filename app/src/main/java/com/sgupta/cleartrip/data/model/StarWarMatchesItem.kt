package com.sgupta.cleartrip.data.model

import com.sgupta.cleartrip.domain.model.StarWarMatchesDomainModel

data class StarWarMatchesItem(
    val match: Int,
    val player1: StarWarPlayersResponseItem,
    val player2: StarWarPlayersResponseItem
)

fun StarWarMatchesItem.toStarWarMatchesDomainModel() = StarWarMatchesDomainModel(
    match = match,
    player1 = player1.toPersonDomainModel(),
    player2 = player2.toPersonDomainModel()
)