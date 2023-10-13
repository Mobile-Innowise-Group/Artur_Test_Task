package com.innowise.testcurrency.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserWalletDTO(
    @SerialName("items")
    val balance: List<UserBalanceItemDTO>,
)
