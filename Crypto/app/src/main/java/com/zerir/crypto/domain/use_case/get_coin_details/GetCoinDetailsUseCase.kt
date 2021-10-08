package com.zerir.crypto.domain.use_case.get_coin_details

import com.zerir.crypto.common.Resource
import com.zerir.crypto.data.remote.dto.toCoinDetails
import com.zerir.crypto.domain.model.CoinDetails
import com.zerir.crypto.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinDetailsUseCase @Inject constructor(
    private val repo: CoinRepository
) {

    operator fun invoke(id: String): Flow<Resource<CoinDetails>> = flow {
        try {
            emit(Resource.Loading())
            val coinDetails = repo.getCoinDetails(id).toCoinDetails()
            emit(Resource.Success(data = coinDetails))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Something went wrong"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check your connection"))
        }
    }

}