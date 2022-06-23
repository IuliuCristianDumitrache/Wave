package com.example.wave.data.repository

import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import com.example.wave.models.Results
import com.example.wave.models.UserResponse
import com.example.wave.network.Resource
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun fetchUsersFlow(page: Int, loadSize: Int): Flow<PagingData<Results>>
}