package com.sgupta.cleartrip.data.model

import com.google.gson.annotations.SerializedName
import com.sgupta.cleartrip.domain.model.PlayerDomainModel

data class StarWarPlayersResponseItem(
    val icon: String? = null,
    val id: Int,
    @SerializedName("name") val name: String? = null,
    @SerializedName("score") val score: Int? = null
)

fun StarWarPlayersResponseItem.toPersonDomainModel() = PlayerDomainModel(
    icon, id, name, score?:0
)