package com.sgupta.cleartrip.feature.leaderboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgupta.cleartrip.domain.model.PlayerDomainModel
import com.sgupta.cleartrip.domain.model.StarWarMatchesDomainModel
import com.sgupta.cleartrip.domain.usecase.GetMatchesUseCase
import com.sgupta.cleartrip.domain.usecase.GetPlayersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaderBoardViewModel @Inject constructor(
    private val getPlayersUseCase: GetPlayersUseCase,
    private val getMatchesUseCase: GetMatchesUseCase
) : ViewModel() {

    private var playersList: MutableList<PlayerDomainModel> = mutableListOf()
    private var matchesList: MutableList<StarWarMatchesDomainModel> = mutableListOf()

    private var _states: MutableSharedFlow<LeaderBoardStates> = MutableSharedFlow()
    val states = _states.asSharedFlow()

    fun getMatchList(id: Int): MutableList<StarWarMatchesDomainModel> {
        val updatedList = matchesList.map { match ->
            val player1 = if ((match.player1.name.isNullOrEmpty())) {
                playersList.find { it.id == match.player1.id } ?: match.player1
            } else {
                match.player1
            }
            val player2 = if ((match.player2.name.isNullOrEmpty())) {
                playersList.find { it.id == match.player2.id } ?: match.player2
            } else {
                match.player2
            }
            match.copy(player1 = player1, player2 = player2)
        }
        matchesList = updatedList.toMutableList()
        return matchesList
    }

    fun getLeaderboard() {
        viewModelScope.launch {
            val playerJob = viewModelScope.launch {
                playersList = getPlayersUseCase.invoke().toMutableList()
            }
            val matchesJob = viewModelScope.launch {
                matchesList = getMatchesUseCase.invoke().toMutableList()
            }
            playerJob.join()
            matchesJob.join()

            for (match in matchesList) {
                val playerOneMatch = match.player1
                val playerTwoMatch = match.player2
                if (playerTwoMatch.score > playerOneMatch.score) {
                    updateWinnerScore(playerTwoMatch.id)
                } else if (playerOneMatch.score > playerTwoMatch.score) {
                    updateWinnerScore(playerOneMatch.id)
                } else {
                    matchDraw(playerOneMatch.id, playerTwoMatch.id)
                }
            }

            val finalList = playersList.sortedByDescending { it.score }
            playersList = finalList.toMutableList()
            _states.emit(LeaderBoardStates.Players(playersList))
        }
    }

    private fun matchDraw(playerOneId: Int, playerTwoId: Int) {
        val updatedList = playersList.map {
            if ((it.id == playerOneId) || (it.id == playerTwoId)) {
                it.copy(score = it.score + 1)
            } else {
                it
            }
        }
        playersList = updatedList.toMutableList()
    }

    private fun updateWinnerScore(id: Int) {
        val updatedList = playersList.map {
            if ((it.id == id)) {
                it.copy(score = it.score + 3)
            } else {
                it
            }
        }
        playersList = updatedList.toMutableList()
    }
}