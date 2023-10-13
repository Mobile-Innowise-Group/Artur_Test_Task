package com.innowise.testcurrency.domain.usecases

import com.innowise.testcurrency.domain.UserWalletRepository
import com.innowise.testcurrency.domain.model.UserWalletModel
import javax.inject.Inject

class GetUserWalletUseCase @Inject constructor(
    private val userWalletRepository: UserWalletRepository,
) {

    suspend fun getCurrencyRates(): Result<UserWalletModel> = userWalletRepository.getWallet()
}