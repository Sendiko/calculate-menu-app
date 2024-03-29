package com.sendiko.calcmenus.splash.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sendiko.calcmenus.R
import com.sendiko.calcmenus.core.navigation.Graphs
import com.sendiko.calcmenus.core.navigation.Routes
import com.sendiko.calcmenus.core.utils.LoginState
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
            contentAlignment = Alignment.BottomCenter
        ){
            Row{
               Spacer(modifier = Modifier.weight(1f))
                Image(
                    modifier = Modifier.weight(3f).padding(bottom = 16.dp),
                    painter = painterResource(id = R.drawable.powered_by),
                    contentDescription = "logo"
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}