package com.innowise.testcurrency.presentation.filter.adapter

import androidx.recyclerview.widget.DiffUtil

class TagsDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areContentsTheSame(
        oldItem: String,
        newItem: String,
    ) = oldItem == newItem

    override fun areItemsTheSame(
        oldItem: String,
        newItem: String,
    ) = oldItem == newItem
}