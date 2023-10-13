package com.innowise.testcurrency.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserWalletResponse(
    @SerialName("result")
    val result: UserWalletDTO,
)
