package com.zerir.crypto.domain.use_case.get_coins

import com.zerir.crypto.common.Resource
import com.zerir.crypto.data.remote.dto.toCoin
import com.zerir.crypto.domain.model.Coin
import com.zerir.crypto.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repo: CoinRepository
) {

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repo.getCoins().map { it.toCoin() }
            emit(Resource.Success(data = coins))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Something went wrong"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check your connection"))
        }
    }

}