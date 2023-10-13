package com.innowise.testcurrency.data.remote.service

import com.innowise.testcurrency.data.remote.dto.UserWalletResponse
import retrofit2.http.GET

interface GetUserWalletService {

    @GET("Mobile-Innowise-Group/Artur_Test_Task/main/wallet_response.json")
    suspend fun getWallet(): UserWalletResponse
}