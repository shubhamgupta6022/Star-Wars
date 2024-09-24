package com.sgupta.cleartrip.data.repo

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sgupta.cleartrip.data.model.StarWarMatchesItem
import com.sgupta.cleartrip.data.model.StarWarPlayersResponseItem
import com.sgupta.cleartrip.data.model.toPersonDomainModel
import com.sgupta.cleartrip.data.model.toStarWarMatchesDomainModel
import com.sgupta.cleartrip.domain.repo.StarWarsRepo
import com.sgupta.cleartrip.domain.model.PlayerDomainModel
import com.sgupta.cleartrip.domain.model.StarWarMatchesDomainModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class StarWarsRepoImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : StarWarsRepo {
    override suspend fun getStarWarPlayers(): List<PlayerDomainModel> {
        val jsonFile = loadJsonFromAsset(context, "StarWarsPlayers.json")
        jsonFile.let {
            val gson = Gson()
            val playersList = object : TypeToken<List<StarWarPlayersResponseItem>>() {}.type
            val listResponse = gson.fromJson<List<StarWarPlayersResponseItem>>(it, playersList)
            return listResponse.map { it.toPersonDomainModel() }
        }
    }

    override suspend fun getStarWarMatches(): List<StarWarMatchesDomainModel> {
        val jsonFile = loadJsonFromAsset(context, "StarWarsMatches.json")
        jsonFile.let {
            val gson = Gson()
            val playersList = object : TypeToken<List<StarWarMatchesItem>>() {}.type
            val matchList = gson.fromJson<List<StarWarMatchesItem>>(it, playersList)
            return matchList.map { it.toStarWarMatchesDomainModel() }
        }
    }

    private fun loadJsonFromAsset(context: Context, fileName: String): String {
        val inputStream = context.assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        return String(buffer, Charsets.UTF_8)
    }
}