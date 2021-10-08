package com.zerir.crypto.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zerir.crypto.common.DataState
import com.zerir.crypto.common.Resource
import com.zerir.crypto.domain.model.Coin
import com.zerir.crypto.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
  private val useCase: GetCoinsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(DataState<List<Coin>>())
    val state: State<DataState<List<Coin>>> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        useCase().onEach { result ->
            when(result){
                is Resource.Loading -> {
                    _state.value = DataState(
                        isLoading = true,
                    )
                }
                is Resource.Success -> {
                    _state.value = DataState(
                        data = result.data ?: emptyList(),
                    )
                }
                is Resource.Error -> {
                    _state.value = DataState(
                        errorMessage = result.message ?: "Something Went Wrong"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}