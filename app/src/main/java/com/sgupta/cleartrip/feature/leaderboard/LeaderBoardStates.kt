package com.sgupta.cleartrip.feature.leaderboard

import com.sgupta.cleartrip.domain.model.PlayerDomainModel

sealed class LeaderBoardStates {
    data class Players(val list: List<PlayerDomainModel>) : LeaderBoardStates()
}