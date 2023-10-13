package com.innowise.testcurrency.data.remote

import com.innowise.testcurrency.data.remote.dto.UserWalletResponse
import com.innowise.testcurrency.data.remote.service.GetUserWalletService
import com.innowise.testcurrency.domain.UserWalletRepository
import com.innowise.testcurrency.domain.model.UserBalanceItem
import com.innowise.testcurrency.domain.model.UserWalletModel
import javax.inject.Inject

class UserWalletRepositoryImpl @Inject constructor(
    private val getUserWalletService: GetUserWalletService,
) : UserWalletRepository {

    override suspend fun getWallet(): Result<UserWalletModel> = runCatching {
        getUserWalletService
            .getWallet()
            .toUserWalletModel()
    }
}

private fun UserWalletResponse.toUserWalletModel() =
    UserWalletModel(
        wallet = result.balance.map {
            UserBalanceItem(
                balance = it.balance,
                equivalent = it.equaivalent,
                imageUrl = it.imageUrl,
                symbol = it.symbol,
                tag = it.tag
            )
        })