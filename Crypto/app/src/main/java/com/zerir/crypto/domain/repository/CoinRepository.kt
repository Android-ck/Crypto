package com.zerir.crypto.domain.repository

import com.zerir.crypto.data.remote.dto.CoinDetailsDto
import com.zerir.crypto.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinDetails(id: String): CoinDetailsDto

}