package com.innowise.testcurrency.presentation.wallet

import com.innowise.testcurrency.domain.model.UserBalanceItem

data class ViewState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val wallet: List<UserBalanceItem> = emptyList(),
)