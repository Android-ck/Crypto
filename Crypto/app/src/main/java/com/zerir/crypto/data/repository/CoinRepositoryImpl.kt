package com.zerir.crypto.data.repository

import com.zerir.crypto.data.remote.dto.CoinDetailsDto
import com.zerir.crypto.data.remote.dto.CoinDto
import com.zerir.crypto.data.remote.ApiResource
import com.zerir.crypto.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: ApiResource
) : CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinDetails(id: String): CoinDetailsDto {
        return api.getCoinDetails(id)
    }
}