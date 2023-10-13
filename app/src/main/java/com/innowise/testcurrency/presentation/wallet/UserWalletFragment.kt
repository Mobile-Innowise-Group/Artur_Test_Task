package com.innowise.testcurrency.presentation.wallet

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.innowise.testcurrency.R
import com.innowise.testcurrency.core.extension.observeWithLifecycle
import com.innowise.testcurrency.databinding.FragmentUserWalletBinding
import com.innowise.testcurrency.presentation.MainActivity
import com.innowise.testcurrency.presentation.filter.PickedFilter
import com.innowise.testcurrency.presentation.wallet.adapter.BalanceItemsAdapter
import com.innowise.testcurrency.presentation.wallet.dialog.FilterDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserWalletFragment : Fragment(R.layout.fragment_user_wallet) {

    private val binding: FragmentUserWalletBinding by viewBinding(FragmentUserWalletBinding::bind)
    private val adapter: BalanceItemsAdapter by lazy { BalanceItemsAdapter() }
    private val viewModel by viewModels<UserWalletViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setClickListeners()
        setObservers()
    }

    private fun initRecyclerView() {
        with(binding) {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }
    }

    private fun setClickListeners() {
        setFragmentResultListener(MainActivity.RESULTS_REQUEST_CODE) { _, bundle ->
            val result = bundle.getParcelable<PickedFilter>(MainActivity.RESULT_KEY)
            viewModel.useFilter(result ?: PickedFilter.Ascending)
        }
        with(binding) {
            error.retryButton.setOnClickListener {
                viewModel.onRetryClicked()
            }
            btnApplyFilter.setOnClickListener {
                FilterDialog(viewModel.tags) { filterType ->
                    findNavController().navigate(UserWalletFragmentDirections.actionCurrencyRateFragmentToFiltersFragment(filterType))
                }.show(childFragmentManager, DIALOG_TAG)
            }
        }
    }

    private fun setObservers() {
        viewModel.uiState.observeWithLifecycle(viewLifecycleOwner) { state ->
            with(binding) {
                progress.isVisible = state.isLoading && !state.isError
                recyclerView.isVisible = !state.isLoading && !state.isError
                btnApplyFilter.isVisible = !state.isLoading && !state.isError
                error.errorLayout.isVisible = state.isError
                adapter.submitList(state.wallet)
            }
        }
        viewModel.uiEffect.observeWithLifecycle(viewLifecycleOwner) {
            Toast.makeText(
                requireContext().applicationContext,
                getString(R.string.error_message),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private companion object {
        const val DIALOG_TAG = "DIALOG_TAG"
    }
}