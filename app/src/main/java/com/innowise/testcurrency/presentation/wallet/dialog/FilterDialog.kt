package com.innowise.testcurrency.presentation.wallet.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.innowise.testcurrency.databinding.FilterDialogBinding

class FilterDialog(
    private val tags: Set<String>,
    private val navigateToFilter: (FilterType) -> Unit,
) : DialogFragment() {

    private lateinit var binding: FilterDialogBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FilterDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        binding.sortBy.setOnClickListener {
            navigateToFilter(FilterType.Equivalent)
            dialog?.let { onDismiss(it) }
        }
        binding.tagSort.setOnClickListener {
            navigateToFilter(FilterType.Tag(tags))
            dialog?.let { onDismiss(it) }
        }
    }

}

