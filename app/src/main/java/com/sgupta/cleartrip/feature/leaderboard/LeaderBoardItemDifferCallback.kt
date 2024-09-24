package com.sgupta.cleartrip.feature.leaderboard

import androidx.recyclerview.widget.DiffUtil
import com.sgupta.cleartrip.domain.model.PlayerDomainModel

class LeaderBoardItemDifferCallback : DiffUtil.ItemCallback<PlayerDomainModel>() {
    override fun areItemsTheSame(oldItem: PlayerDomainModel, newItem: PlayerDomainModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: PlayerDomainModel,
        newItem: PlayerDomainModel
    ): Boolean {
        return oldItem == newItem
    }
}