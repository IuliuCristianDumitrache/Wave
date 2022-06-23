package com.example.wave.ui.users

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.wave.data.remotedatasource.UsersRemoteDataSource
import com.example.wave.models.Results
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersPagingSource @Inject constructor(
    private val remoteDataSource: UsersRemoteDataSource
): PagingSource<Int, Results>() {

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        return try {
            val page = params.key ?: FIRST_PAGE_INDEX
            val result = remoteDataSource.fetchUsersData(page, params.loadSize)
            LoadResult.Page(
                data = result.data?.results ?: listOf(),
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (result.data?.results.isNullOrEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}