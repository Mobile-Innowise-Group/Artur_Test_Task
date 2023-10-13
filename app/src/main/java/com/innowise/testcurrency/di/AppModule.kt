package com.innowise.testcurrency.di

import com.innowise.testcurrency.data.remote.UserWalletRepositoryImpl
import com.innowise.testcurrency.data.remote.service.GetUserWalletService
import com.innowise.testcurrency.domain.UserWalletRepository
import com.innowise.testcurrency.domain.usecases.GetUserWalletUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideUserWalletRepository(
        getUserWalletService: GetUserWalletService,
    ): UserWalletRepository = UserWalletRepositoryImpl(getUserWalletService)

    @Provides
    fun provideGetUserWalletUseCase(
        userWalletRepository: UserWalletRepository,
    ): GetUserWalletUseCase = GetUserWalletUseCase(userWalletRepository)
}