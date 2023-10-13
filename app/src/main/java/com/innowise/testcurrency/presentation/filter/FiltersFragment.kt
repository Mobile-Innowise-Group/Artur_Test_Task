package com.innowise.testcurrency.presentation.filter

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.innowise.testcurrency.R
import com.innowise.testcurrency.databinding.FragmentFiltersBinding
import com.innowise.testcurrency.presentation.MainActivity
import com.innowise.testcurrency.presentation.filter.adapter.TagsAdapter
import com.innowise.testcurrency.presentation.wallet.dialog.FilterType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FiltersFragment : Fragment(R.layout.fragment_filters) {

    private val binding by viewBinding(FragmentFiltersBinding::bind)
    private val arguments by navArgs<FiltersFragmentArgs>()
    private val filterType by lazy { arguments.filterType }
    private val tagsAdapter: TagsAdapter by lazy {
        TagsAdapter {
            currentFilter = PickedFilter.Tags(it)
        }
    }
    private lateinit var currentFilter: PickedFilter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            when (filterType) {
                is FilterType.Tag -> initRecyclerView((filterType as FilterType.Tag).tags)
                is FilterType.Equivalent -> initEquivalentView()
            }
            btnApplyFilter.setOnClickListener {
                setFragmentResult(
                    MainActivity.RESULTS_REQUEST_CODE,
                    bundleOf(MainActivity.RESULT_KEY to currentFilter)
                )
                findNavController().popBackStack()
            }
        }
    }

    private fun initRecyclerView(tags: Set<String>) {
        with(binding) {
            currentFilter = PickedFilter.Tags(tags)
            recyclerViewFilters.apply {
                isVisible = true
                adapter = tagsAdapter
                layoutManager = LinearLayoutManager(requireContext())
                addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            }
            tagsAdapter.submitList(tags.toList())
        }
    }

    private fun initEquivalentView() {
        with(binding) {
            layoutEquivalentFilters.root.isVisible = true
            currentFilter = PickedFilter.Ascending
            layoutEquivalentFilters.chooser.setOnCheckedChangeListener { _, checkedId ->
                currentFilter = when (checkedId) {
                    R.id.rbtn_filter_ascending -> PickedFilter.Ascending
                    R.id.rbtn_filter_descending -> PickedFilter.Descending
                    else -> throw IllegalArgumentException()
                }
            }
        }
    }
}