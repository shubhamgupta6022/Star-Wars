package com.sgupta.cleartrip.feature.matchelist

import androidx.recyclerview.widget.DiffUtil
import com.sgupta.cleartrip.domain.model.PlayerDomainModel
import com.sgupta.cleartrip.domain.model.StarWarMatchesDomainModel

class MatchAdapterItemDifferCallback : DiffUtil.ItemCallback<StarWarMatchesDomainModel>() {
    override fun areItemsTheSame(oldItem: StarWarMatchesDomainModel, newItem: StarWarMatchesDomainModel): Boolean {
        return oldItem.match == newItem.match
    }

    override fun areContentsTheSame(
        oldItem: StarWarMatchesDomainModel,
        newItem: StarWarMatchesDomainModel
    ): Boolean {
        return oldItem == newItem
    }
}