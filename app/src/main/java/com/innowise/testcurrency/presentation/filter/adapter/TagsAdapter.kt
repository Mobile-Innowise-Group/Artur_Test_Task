package com.innowise.testcurrency.presentation.filter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.innowise.testcurrency.databinding.ListItemTagsBinding

class TagsAdapter(
    private val onCheckedChanged: (MutableSet<String>) -> Unit,
) : ListAdapter<String, TagsAdapter.TagFilterViewHolder>(TagsDiffCallback()) {

    private val tags: MutableSet<String> = mutableSetOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagFilterViewHolder =
        TagFilterViewHolder(
            ListItemTagsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: TagFilterViewHolder, position: Int) {
        with(holder.binding) {
            tvTagName.text = getItem(position)
            tags.addAll(currentList)
            tvTagName.setOnCheckedChangeListener { _, isChecked ->
                changeTagSet(isChecked, getItem(position))
            }
        }
    }

    private fun changeTagSet(isChecked: Boolean, tag: String) {
        if (!isChecked) {
            tags.remove(tag)
        } else {
            tags.add(tag)
        }
        onCheckedChanged.invoke(tags)
    }

    class TagFilterViewHolder(val binding: ListItemTagsBinding) : RecyclerView.ViewHolder(binding.root)
}