package com.zerir.crypto.data.remote

import com.zerir.crypto.data.remote.dto.CoinDetailsDto
import com.zerir.crypto.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiResource {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinDetails(@Path("coinId") coinId: String): CoinDetailsDto
}