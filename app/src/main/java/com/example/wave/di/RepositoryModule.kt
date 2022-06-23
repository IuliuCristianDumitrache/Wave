package com.example.wave.di

import com.example.wave.data.repository.UsersRepository
import com.example.wave.data.repository.UsersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindUsersRepository(exchangeRepositoryImpl: UsersRepositoryImpl): UsersRepository
}