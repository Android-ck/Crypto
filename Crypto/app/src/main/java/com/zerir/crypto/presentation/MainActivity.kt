package com.zerir.crypto.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zerir.crypto.common.Constants
import com.zerir.crypto.presentation.coin_details.CoinDetailScreen
import com.zerir.crypto.presentation.coin_list.CoinListScreen
import com.zerir.crypto.presentation.ui.theme.CryptoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.CoinListScreen.route){
                        composable(route = Screen.CoinListScreen.route){
                            CoinListScreen(navController = navController)
                        }
                        composable(route = Screen.CoinDetailsScreen.route + "/{${Constants.PARAM_COIN_ID}}"){
                            CoinDetailScreen()
                        }
                    }
                }
            }
        }
    }
}