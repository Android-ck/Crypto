package com.zerir.crypto.presentation.coin_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zerir.crypto.common.Constants
import com.zerir.crypto.common.DataState
import com.zerir.crypto.common.Resource
import com.zerir.crypto.domain.model.CoinDetails
import com.zerir.crypto.domain.use_case.get_coin_details.GetCoinDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
  private val useCase: GetCoinDetailsUseCase, savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = mutableStateOf(DataState<CoinDetails>())
    val state: State<DataState<CoinDetails>> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { id ->
            getCoinDetails(id)
        }
    }

    private fun getCoinDetails(id: String) {
        useCase(id).onEach { result ->
            when(result){
                is Resource.Loading -> {
                    _state.value = DataState(
                        isLoading = true,
                    )
                }
                is Resource.Success -> {
                    _state.value = DataState(
                        data = result.data,
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