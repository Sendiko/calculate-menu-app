package com.sendiko.calcmenus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.sendiko.calcmenus.ui.screens.Graphs
import com.sendiko.calcmenus.ui.screens.Routes
import com.sendiko.calcmenus.ui.screens.restaurant.WelcomeResto
import com.sendiko.calcmenus.ui.screens.welcome.WelcomeScreen
import com.sendiko.calcmenus.ui.screens.welcome.WelcomeScreenEvents
import com.sendiko.calcmenus.ui.theme.CalcMenusTheme
import com.sendiko.calcmenus.ui.theme.NotWhite

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalcMenusTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = NotWhite
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Routes.WelcomeScreenRoute.route,
                        builder = {
                            composable(
                                route = Routes.WelcomeScreenRoute.route,
                                content = {
                                    WelcomeScreen(
                                        onNavigate = { route ->
                                            navController.navigate(route = Graphs.RestoAuthGraph.graph)
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
                                                onNavigate = {

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
        }
    }
}