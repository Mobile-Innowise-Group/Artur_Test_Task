package com.innowise.testcurrency.presentation.wallet.adapter

import androidx.recyclerview.widget.DiffUtil
import com.innowise.testcurrency.domain.model.UserBalanceItem

class BalanceItemsDiffCallback : DiffUtil.ItemCallback<UserBalanceItem>() {

    override fun areContentsTheSame(
        oldItem: UserBalanceItem,
        newItem: UserBalanceItem,
    ) = oldItem == newItem

    override fun areItemsTheSame(
        oldItem: UserBalanceItem,
        newItem: UserBalanceItem,
    ) = oldItem.balance == newItem.balance
}