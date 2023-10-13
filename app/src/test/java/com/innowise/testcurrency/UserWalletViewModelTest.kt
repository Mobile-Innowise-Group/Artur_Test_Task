package com.innowise.testcurrency

import com.innowise.testcurrency.domain.usecases.GetUserWalletUseCase
import com.innowise.testcurrency.presentation.wallet.UserWalletViewModel
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class UserWalletViewModelTest {

    private val getUserWalletUseCase: GetUserWalletUseCase = mockk(relaxed = true)
    private lateinit var viewModel: UserWalletViewModel

    @Before
    fun setup() {
        viewModel = UserWalletViewModel(
            getUserWalletUseCase
        )
    }

    @Test
    fun test() {

    }
}