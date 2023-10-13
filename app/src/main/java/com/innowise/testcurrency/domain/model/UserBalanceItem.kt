package com.innowise.testcurrency.domain.model

data class UserBalanceItem(
    val balance: Double,
    val equivalent: Double,
    val imageUrl: String,
    val symbol: String,
    val tag: String,
)
