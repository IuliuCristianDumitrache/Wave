package com.example.wave.di

import android.content.Context
import com.example.wave.data.remotedatasource.UsersRemoteDataSource
import com.example.wave.data.repository.UsersRepositoryImpl
import com.example.wave.network.ApiFactory
import com.example.wave.network.ApiService
import com.example.wave.network.RemoteServicesHandler
import com.example.wave.network.exceptions.NetworkExceptionHandler
import com.example.wave.ui.users.UsersPagingSource
import com.example.wave.utils.StringResource
import com.example.wave.utils.StringResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun provideApiService(): ApiService {
        return ApiFactory.getClient()
    }

    @Provides
    fun provideStringResource(@ApplicationContext context: Context): StringResource {
        return StringResourceProvider(context)
    }

    @Provides
    fun provideUsersRemoteDataSource(
        apiService: ApiService,
        handler: RemoteServicesHandler
    ): UsersRemoteDataSource {
        return UsersRemoteDataSource(apiService, handler)
    }

    @Provides
    fun provideRemoteServiceHandler(networkExceptionHandler: NetworkExceptionHandler): RemoteServicesHandler {
        return RemoteServicesHandler(networkExceptionHandler)
    }

    @Provides
    fun provideNetworkExceptionHandler(stringResource: StringResource): NetworkExceptionHandler {
        return NetworkExceptionHandler(stringResource)
    }

    @Provides
    fun provideUsersRepository(remoteDataSource: UsersRemoteDataSource, usersPagingSource: UsersPagingSource): UsersRepositoryImpl {
        return UsersRepositoryImpl(remoteDataSource, usersPagingSource)
    }

    @Provides
    fun provideUsersPagingSource(remoteDataSource: UsersRemoteDataSource): UsersPagingSource {
        return UsersPagingSource(remoteDataSource)
    }
}