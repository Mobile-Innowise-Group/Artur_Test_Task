package com.innowise.testcurrency.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserBalanceItemDTO(
    @SerialName("balance")
    val balance: Double,
    @SerialName("equaivalent")
    val equaivalent: Double,
    @SerialName("image")
    val imageUrl: String,
    @SerialName("symbol")
    val symbol: String,
    @SerialName("tag")
    val tag: String,
)
