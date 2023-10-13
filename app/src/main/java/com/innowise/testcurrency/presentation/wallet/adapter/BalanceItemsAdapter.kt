package com.innowise.testcurrency.presentation.wallet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.innowise.testcurrency.R
import com.innowise.testcurrency.databinding.ListItemBalanceBinding
import com.innowise.testcurrency.domain.model.UserBalanceItem

class BalanceItemsAdapter : ListAdapter<UserBalanceItem, BalanceItemsAdapter.BalanceItemViewHolder>(BalanceItemsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BalanceItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_balance, parent, false)
        return BalanceItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: BalanceItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class BalanceItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding by viewBinding(ListItemBalanceBinding::bind)

        fun bind(item: UserBalanceItem) {
            with(binding) {
                Glide
                    .with(root.context)
                    .load(item.imageUrl)
                    .circleCrop()
                    .error(R.drawable.ic_launcher_foreground)
                    .into(imageView)
                tag.text = item.tag
                equaivalent.text =
                    root.context.getString(R.string.usd, item.equivalent.toString())
                balance.text =
                    root.context.getString(
                        R.string.symbol_format,
                        item.balance.toString(),
                        item.symbol
                    )
                balanceItemName.text = item.symbol
            }
        }
    }
}