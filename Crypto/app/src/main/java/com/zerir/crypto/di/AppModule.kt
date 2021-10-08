package com.zerir.crypto.di

import com.zerir.crypto.common.Constants
import com.zerir.crypto.data.remote.ApiResource
import com.zerir.crypto.data.repository.CoinRepositoryImpl
import com.zerir.crypto.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiResources(): ApiResource = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiResource::class.java)


    @Provides
    @Singleton
    fun provideCoinRepository(api: ApiResource): CoinRepository = CoinRepositoryImpl(api)
}