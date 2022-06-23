package com.example.wave.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.wave.data.remotedatasource.UsersRemoteDataSource
import com.example.wave.models.Results
import com.example.wave.ui.users.UsersPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersRepositoryImpl @Inject constructor(
    private val remoteDataSource: UsersRemoteDataSource,
    private val usersPagingSource: UsersPagingSource
) : UsersRepository {

    override suspend fun fetchUsersFlow(page: Int, loadSize: Int): Flow<PagingData<Results>> {
        return Pager(config = PagingConfig(page, loadSize),
            pagingSourceFactory = { usersPagingSource }).flow
    }
}