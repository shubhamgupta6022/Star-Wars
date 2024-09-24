package com.sgupta.cleartrip.di

import com.sgupta.cleartrip.data.repo.StarWarsRepoImpl
import com.sgupta.cleartrip.domain.repo.StarWarsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {
    @Binds
    abstract fun bindStarWarRepoImpl(impl: StarWarsRepoImpl): StarWarsRepo
}