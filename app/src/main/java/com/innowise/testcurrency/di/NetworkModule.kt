package com.innowise.testcurrency.di

import com.innowise.testcurrency.data.remote.service.GetUserWalletService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

private const val BASE_URL = "https://raw.githubusercontent.com/"
private const val APPLICATION_JSON = "application/json"

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .build()

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideJson(): Json =
        Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
            isLenient = true
        }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideRetrofit(
        json: Json,
        okHttpClient: OkHttpClient,
    ): Retrofit =
        Retrofit
            .Builder()
            .addConverterFactory(json.asConverterFactory(APPLICATION_JSON.toMediaType()))
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()

    @Provides
    @Singleton
    fun provideCurrenciesService(retrofit: Retrofit): GetUserWalletService =
        retrofit.create(GetUserWalletService::class.java)
}