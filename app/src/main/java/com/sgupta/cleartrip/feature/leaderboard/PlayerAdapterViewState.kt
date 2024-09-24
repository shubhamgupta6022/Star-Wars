package com.sgupta.cleartrip.feature.leaderboard

sealed class PlayerAdapterViewState {
    data class OnItemClicked(val id: Int) : PlayerAdapterViewState()
}