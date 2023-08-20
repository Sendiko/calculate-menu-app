package com.sendiko.calcmenus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.sendiko.calcmenus.ui.screens.Graphs
import com.sendiko.calcmenus.ui.screens.Routes
import com.sendiko.calcmenus.ui.screens.employee.menu_screen.MenuScreen
import com.sendiko.calcmenus.ui.screens.restaurant.RegisterScreen
import com.sendiko.calcmenus.ui.screens.restaurant.WelcomeResto
import com.sendiko.calcmenus.ui.screens.welcome.WelcomeScreen
import com.sendiko.calcmenus.ui.screens.welcome.WelcomeScreenEvents
import com.sendiko.calcmenus.ui.theme.CalcMenusTheme
import com.sendiko.calcmenus.ui.theme.NotWhite
import com.sendiko.calcmenus.ui.theme.PrimaryRed
import com.sendiko.calcmenus.ui.screens.employee.LoginScreen as EmployeeLoginScreen
import com.sendiko.calcmenus.ui.screens.restaurant.LoginScreen as RestoLoginScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(
            window,
            false
        )

        setContent {
            var outsideApp by remember { mutableStateOf(true) }
            CalcMenusTheme(
                outsideApp = outsideApp,
                content = {
                    val navController = rememberNavController()
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = if (outsideApp) NotWhite else PrimaryRed
                    ) {
                        val currentRoute = navController.currentDestination?.route
                        outsideApp = when (currentRoute) {
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
                                                RestoLoginScreen(
                                                    onLogin = {

                                                    }
                                                )
                                            }
                                        )
                                        composable(
                                            route = Routes.RestoRegister.route,
                                            content = {
                                                var registerPart by remember { mutableStateOf(1) }
                                                RegisterScreen(
                                                    onRegister = { /*TODO*/ },
                                                    onNavigatePart = { part ->
                                                        registerPart = part
                                                    },
                                                    registerPart = registerPart
                                                )
                                            }
                                        )
                                    }
                                )
                                navigation(
                                    route = Graphs.EmpAuthGraph.graph,
                                    startDestination = Routes.EmployeeLogin.route,
                                    builder = {
                                        composable(
                                            route = Routes.EmployeeLogin.route,
                                            content = {
                                                EmployeeLoginScreen(
                                                    onLogin = { route ->
                                                        navController.navigate(
                                                            route = route
                                                        ){
                                                            popUpTo(
                                                                navController.graph.id,
                                                            ){ inclusive = true }
                                                        }
                                                    }
                                                )
                                            }
                                        )
                                    }
                                )
                                navigation(
                                    route = Graphs.EmpMainGraph.graph,
                                    startDestination = Routes.EmployeeMenuScreen.route,
                                    builder = {
                                        composable(
                                            route = Routes.EmployeeMenuScreen.route,
                                            content = {
                                                MenuScreen (
                                                    onPlaceOrder = {

                                                    }
                                                )
                                            }
                                        )
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