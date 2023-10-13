package com.innowise.testcurrency.domain

import com.innowise.testcurrency.domain.model.UserWalletModel

interface UserWalletRepository {

    suspend fun getWallet(): Result<UserWalletModel>
}