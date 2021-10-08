package com.zerir.crypto.presentation

sealed class Screen(val route: String) {
    object CoinListScreen: Screen("coin-screen")
    object CoinDetailsScreen: Screen("coin-details")
}
