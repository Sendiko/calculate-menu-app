package com.sendiko.calcmenus

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
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
import com.sendiko.calcmenus.repository.viewmodels.EmployeeLoginViewModel
import com.sendiko.calcmenus.ui.screens.Graphs
import com.sendiko.calcmenus.ui.screens.Routes
import com.sendiko.calcmenus.ui.screens.employee.login.EmployeeLoginScreen
import com.sendiko.calcmenus.ui.screens.employee.menu_screen.MenuScreen
import com.sendiko.calcmenus.ui.screens.employee.ongoing_order.OnGoingOrderScreen
import com.sendiko.calcmenus.ui.screens.employee.order_resume.OrderResumeScreen
import com.sendiko.calcmenus.ui.screens.employee.post_screen.PostDeliverScreen
import com.sendiko.calcmenus.ui.screens.employee.post_screen.PostOrderResumeScreen
import com.sendiko.calcmenus.ui.screens.employee.post_screen.PostPayedScreen
import com.sendiko.calcmenus.ui.screens.employee.profile.ProfileScreen
import com.sendiko.calcmenus.ui.screens.restaurant.WelcomeResto
import com.sendiko.calcmenus.ui.screens.restaurant.auth.RegisterScreen
import com.sendiko.calcmenus.ui.screens.restaurant.main.DashboardScreen
import com.sendiko.calcmenus.ui.screens.restaurant.main.employee.CreateEmployeeScreen
import com.sendiko.calcmenus.ui.screens.restaurant.main.employee.ViewEmployeeScreen
import com.sendiko.calcmenus.ui.screens.restaurant.main.menu.CreateMenuScreen
import com.sendiko.calcmenus.ui.screens.restaurant.main.menu.EditMenuScreen
import com.sendiko.calcmenus.ui.screens.welcome.WelcomeScreen
import com.sendiko.calcmenus.ui.screens.welcome.WelcomeScreenEvents
import com.sendiko.calcmenus.ui.theme.CalcMenusTheme
import com.sendiko.calcmenus.ui.theme.NotWhite
import com.sendiko.calcmenus.ui.theme.PrimaryRed
import com.sendiko.calcmenus.ui.screens.restaurant.auth.LoginScreen as RestoLoginScreen
import com.sendiko.calcmenus.ui.screens.restaurant.profile.ProfileScreen as RestoProfileScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(
            window,
            false
        )

        setContent {
            var outsideApp by remember { mutableStateOf(true) }
            val employeeLoginViewModel by viewModels<EmployeeLoginViewModel>()
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
                                                    onLogin = { route ->
                                                        navController.navigate(
                                                            route = route
                                                        ) {
                                                            popUpTo(
                                                                navController.graph.id,
                                                            ) { inclusive = true }
                                                        }
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
                                                    state = employeeLoginViewModel.state.collectAsState().value,
                                                    onEvents = employeeLoginViewModel::onEvent,
                                                    navController = navController
                                                )
                                            }
                                        )
                                    }
                                )
                                navigation(
                                    route = Graphs.RestoMainGraph.graph,
                                    startDestination = Routes.RestoDashboardScreen.route,
                                    builder = {
                                        composable(
                                            route = Routes.RestoProfileScreen.route,
                                            content = {
                                                RestoProfileScreen(
                                                    onNavigateBack = { route ->
                                                        navController.navigate(route)
                                                    }
                                                )
                                            }
                                        )
                                        composable(
                                            route = Routes.RestoDashboardScreen.route,
                                            content = {
                                                DashboardScreen(
                                                    onCreateMenu = { route ->
                                                        navController.navigate(route)
                                                    },
                                                    onEditMenu = { route ->
                                                        navController.navigate(route)
                                                    },
                                                    onEmployeeDetails = { route ->
                                                        navController.navigate(route)
                                                    },
                                                    onCreateEmployee = { route ->
                                                        navController.navigate(route)
                                                    },
                                                    onNavigate = { route ->
                                                        navController.navigate(route)
                                                    }
                                                )
                                            }
                                        )
                                        composable(
                                            route = Routes.RestoCreateMenuScreen.route,
                                            content = {
                                                CreateMenuScreen(
                                                    onNavigateBack = { route ->
                                                        navController.navigate(route)
                                                    },
                                                    onMenuCreated = {
                                                        Toast.makeText(
                                                            applicationContext,
                                                            "IconButtonClicked",
                                                            Toast.LENGTH_SHORT
                                                        ).show()

                                                    }
                                                )
                                            }
                                        )
                                        composable(
                                            route = Routes.RestoEditMenuScreen.route,
                                            content = {
                                                EditMenuScreen(
                                                    onNavigateBack = { route ->
                                                        navController.navigate(route)
                                                    },
                                                    onMenuUpdated = {}
                                                )
                                            }
                                        )
                                        composable(
                                            route = Routes.RestoViewEmployeeScreen.route,
                                            content = {
                                                ViewEmployeeScreen(
                                                    onNavigateBack = { route ->
                                                        navController.navigate(route)
                                                    }
                                                )
                                            }
                                        )
                                        composable(
                                            route = Routes.RestoCreateEmployeeScreen.route,
                                            content = {
                                                CreateEmployeeScreen(
                                                    onNavigateBack = { route ->
                                                        navController.navigate(route)
                                                    },
                                                    onEmployeeCreated = {

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
                                            route = Routes.EmployeeProfileScreen.route,
                                            content = {
                                                ProfileScreen(onNavigateBack = { route ->
                                                    navController.navigate(route )
                                                })
                                            }
                                        )
                                        composable(
                                            route = Routes.EmployeeMenuScreen.route,
                                            content = {
                                                MenuScreen(
                                                    onNavigate = { route ->
                                                        navController.navigate(route)
                                                    },
                                                    onPlaceOrder = { route ->
                                                        navController.navigate(route)
                                                    }
                                                )
                                            }
                                        )
                                        composable(
                                            route = Routes.EmployeeOngoingOrdersScreen.route,
                                            content = {
                                                OnGoingOrderScreen(
                                                    onNavigate = { route ->
                                                        navController.navigate(route)
                                                    }
                                                )
                                            }
                                        )
                                        composable(
                                            route = Routes.EmployeeOrderResumeScreen.route,
                                            content = {
                                                OrderResumeScreen(
                                                    onAddMoreMenu = { route ->
                                                        navController.navigate(route)
                                                    },
                                                    onPlaceOrder = {
                                                        navController.navigate(Routes.EmployeePostOrderScreen.route)
                                                    }
                                                )
                                            }
                                        )
                                        composable(
                                            route = Routes.EmployeePostOrderScreen.route,
                                            content = {
                                                PostOrderResumeScreen(
                                                    navController = navController
                                                )
                                            }
                                        )
                                        composable(
                                            route = Routes.EmployeePostDeliverScreen.route,
                                            content = {
                                                PostDeliverScreen(
                                                    navController = navController
                                                )
                                            }
                                        )
                                        composable(
                                            route = Routes.EmployeePostPayedScreen.route,
                                            content = {
                                                PostPayedScreen(
                                                    navController = navController
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