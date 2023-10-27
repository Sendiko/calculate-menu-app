package com.sendiko.calcmenus.ui.screens.welcome

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sendiko.calcmenus.R
import com.sendiko.calcmenus.ui.screens.Graphs
import com.sendiko.calcmenus.ui.screens.Routes
import com.sendiko.calcmenus.ui.utils.LoginState
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun SplashScreen(
    state: SplashScreenState,
    navController: NavController = rememberNavController()
) {
    Log.i("LOGIN_STATE", "state in SplashScreen: ${state.isLoggedIn}")
    LaunchedEffect(
        key1 = state.isLoggedIn,
        block = {
            // to avoid race condition
            delay(1.seconds)
            when(state.isLoggedIn){
                LoginState.EmployeeAccount.account -> {
                    navController.navigate(
                        route = Graphs.EmpMainGraph.graph
                    ) {
                        popUpTo(
                            navController.graph.id,
                        ) { inclusive = true }
                    }
                }
                LoginState.RestaurantAccount.account -> {
                    navController.navigate(
                        route = Graphs.RestoMainGraph.graph
                    ) {
                        popUpTo(
                            navController.graph.id,
                        ) { inclusive = true }
                    }
                }
                else -> {
                    navController.navigate(
                        route = Routes.WelcomeScreenRoute.route
                    ) {
                        popUpTo(
                            navController.graph.id,
                        ) { inclusive = true }
                    }
                }
            }
        }
    )
    Scaffold {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Image(painter = painterResource(id = R.drawable.sss_logo), contentDescription = "logo")
        }
    }
}