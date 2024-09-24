package com.sgupta.cleartrip.feature.leaderboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sgupta.cleartrip.databinding.PlayerLeaderboardItemBinding
import com.sgupta.cleartrip.domain.model.PlayerDomainModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LeaderBoardAdapter :
    ListAdapter<PlayerDomainModel, LeaderBoardAdapter.ViewHolder>(LeaderBoardItemDifferCallback()) {

    private var _uiStates : MutableSharedFlow<PlayerAdapterViewState> = MutableSharedFlow()
    val uiState = _uiStates.asSharedFlow()

    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            PlayerLeaderboardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class ViewHolder(private val binding: PlayerLeaderboardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(model: PlayerDomainModel) {
            with(binding) {
                tvPlayerName.text = model.name
                tvPlayerScore.text = model.score.toString()
                Glide.with(this.root.context)
                    .load(model.icon)
                    .into(ivPlayer)
                itemView.setOnClickListener {
                    scope.launch {
                        _uiStates.emit(PlayerAdapterViewState.OnItemClicked(model.id))
                    }
                }
            }
        }
    }
}