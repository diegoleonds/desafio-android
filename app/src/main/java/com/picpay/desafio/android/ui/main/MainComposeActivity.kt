package com.picpay.desafio.android.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.picpay.desafio.android.ui.baseui.theme.PicPayTheme
import com.picpay.desafio.android.ui.userlist.UserListScreen
import org.koin.androidx.compose.getViewModel

class MainComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PicPayTheme {
                val navController = rememberNavController()
                NavigationComponent(navController)
            }
        }
    }
}

@Composable
private fun NavigationComponent(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "userList"
    ) {
        composable("userList") {
            UserListScreen(viewModel = getViewModel())
        }
    }
}