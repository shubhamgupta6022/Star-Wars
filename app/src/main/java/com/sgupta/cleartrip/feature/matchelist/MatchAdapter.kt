package com.sgupta.cleartrip.feature.matchelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sgupta.cleartrip.R
import com.sgupta.cleartrip.databinding.MatchListItemBinding
import com.sgupta.cleartrip.databinding.PlayerLeaderboardItemBinding
import com.sgupta.cleartrip.domain.model.PlayerDomainModel
import com.sgupta.cleartrip.domain.model.StarWarMatchesDomainModel
import com.sgupta.cleartrip.feature.leaderboard.PlayerAdapterViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MatchAdapter :
    ListAdapter<StarWarMatchesDomainModel, MatchAdapter.ViewHolder>(MatchAdapterItemDifferCallback()) {

    private var _uiStates: MutableSharedFlow<PlayerAdapterViewState> = MutableSharedFlow()
    val uiState = _uiStates.asSharedFlow()

    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            MatchListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class ViewHolder(private val binding: MatchListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(model: StarWarMatchesDomainModel) {
            with(binding) {
                tvPlayer1Name.text = model.player1.name
                tvPlayer1Score.text = model.player1.score.toString()
                tvPlayer2Name.text = model.player1.name
                tvPlayer2Score.text = model.player1.score.toString()
                if (model.player1.score > model.player2.score) {
                    root.setBackgroundColor(this.root.context.getColor(R.color.teal))
                } else if (model.player2.score > model.player1.score) {
                    root.setBackgroundColor(this.root.context.getColor(R.color.red))
                } else {
                    root.setBackgroundColor(this.root.context.getColor(R.color.white))
                }
            }
        }
    }
}