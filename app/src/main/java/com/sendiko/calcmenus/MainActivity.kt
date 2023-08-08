package com.sendiko.calcmenus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.sendiko.calcmenus.ui.screens.Graphs
import com.sendiko.calcmenus.ui.screens.Routes
import com.sendiko.calcmenus.ui.screens.restaurant.LoginScreen
import com.sendiko.calcmenus.ui.screens.restaurant.WelcomeResto
import com.sendiko.calcmenus.ui.screens.welcome.WelcomeScreen
import com.sendiko.calcmenus.ui.screens.welcome.WelcomeScreenEvents
import com.sendiko.calcmenus.ui.theme.CalcMenusTheme
import com.sendiko.calcmenus.ui.theme.NotWhite

class MainActivity : ComponentActivity() {

    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(
            window,
            false
        )

        val outsideApp = mutableStateOf(true)

        setContent {
            CalcMenusTheme(
                outsideApp = outsideApp.value,
                content = {
                    val navController = rememberNavController()
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = NotWhite
                    ) {
                        val currentRoute = navController.currentDestination?.route
                        outsideApp.value = when (currentRoute) {
                            Routes.WelcomeScreenRoute.route -> true
                            Graphs.RestoAuthGraph.graph -> true
                            Graphs.EmpAuthGraph.graph -> true
                            Routes.RestoWelcome.route -> true
                            Routes.EmployeeLogin.route -> true
                            else -> false
                        }
                        NavHost(
                            navController = navController,
                            startDestination = Routes.WelcomeScreenRoute.route,
                            builder = {
                                composable(
                                    route = Routes.WelcomeScreenRoute.route,
                                    content = {
                                        WelcomeScreen(
                                            onNavigate = { route ->
                                                navController.navigate(route = route)
                                                WelcomeScreenEvents.OnNavigate(route)
                                            }
                                        )
                                    }
                                )
                                navigation(
                                    route = Graphs.RestoAuthGraph.graph,
                                    startDestination = Routes.RestoWelcome.route,
                                    builder = {
                                        composable(
                                            route = Routes.RestoWelcome.route,
                                            content = {
                                                WelcomeResto(
                                                    onNavigate = { route ->
                                                        navController.navigate(route = route)
                                                    }
                                                )
                                            }
                                        )
                                        composable(
                                            route = Routes.RestoLogin.route,
                                            content = {
                                                LoginScreen(
                                                    onNavigate = {

                                                    },
                                                    onLogin = {

                                                    }
                                                )
                                            }
                                        )
                                    }
                                )
                                navigation(
                                    route = Graphs.EmpAuthGraph.graph,
                                    startDestination = Routes.EmployeeLogin.route,
                                    builder = {

                                    }
                                )
                            }
                        )
                    }
                }
            )
        }
    }
}