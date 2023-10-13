package com.innowise.testcurrency.presentation.wallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.innowise.testcurrency.domain.model.UserBalanceItem
import com.innowise.testcurrency.domain.usecases.GetUserWalletUseCase
import com.innowise.testcurrency.presentation.filter.PickedFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserWalletViewModel @Inject constructor(
    private val getUserWalletUseCase: GetUserWalletUseCase,
) : ViewModel() {

    private val wallet: MutableList<UserBalanceItem> = mutableListOf()
    val tags: MutableSet<String> = mutableSetOf()

    private val _uiState = MutableStateFlow(ViewState())
    val uiState = _uiState.asStateFlow()

    private val _uiEffect = Channel<Unit>(Channel.BUFFERED)
    val uiEffect = _uiEffect.receiveAsFlow()

    init {
        getWallet()
    }

    private fun getWallet() = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true, isError = false) }

        getUserWalletUseCase.getCurrencyRates()
            .onSuccess { userWallet ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isError = false,
                        wallet = userWallet.wallet
                    )
                }
                wallet.addAll(userWallet.wallet)
                tags.addAll(wallet.map { it.tag })
            }
            .onFailure {
                _uiState.update { it.copy(isLoading = false, isError = true) }
                _uiEffect.send(Unit)
            }
    }

    fun onRetryClicked() {
        getWallet()
    }

    fun useFilter(pickedFilter: PickedFilter) {
        _uiState.update { state ->
            state.copy(wallet = when (pickedFilter) {
                PickedFilter.Ascending -> wallet.sortedBy { it.equivalent }
                PickedFilter.Descending -> wallet.sortedByDescending { it.equivalent }
                is PickedFilter.Tags -> wallet.filter { it.tag in pickedFilter.tags }
            })
        }
    }
}