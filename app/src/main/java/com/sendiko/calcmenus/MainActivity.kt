package com.sendiko.calcmenus

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.sendiko.calcmenus.core.navigation.Graphs
import com.sendiko.calcmenus.core.navigation.Routes
import com.sendiko.calcmenus.core.theme.CalcMenusTheme
import com.sendiko.calcmenus.core.theme.NotWhite
import com.sendiko.calcmenus.core.theme.PrimaryRed
import com.sendiko.calcmenus.employee.login.presentation.EmployeeLoginScreen
import com.sendiko.calcmenus.employee.login.presentation.EmployeeLoginViewModel
import com.sendiko.calcmenus.employee.menu.data.MenusItem
import com.sendiko.calcmenus.employee.menu.presentation.EmployeeMenuViewModel
import com.sendiko.calcmenus.employee.menu.presentation.MenuScreen
import com.sendiko.calcmenus.employee.menu.presentation.MenuScreenEvent
import com.sendiko.calcmenus.employee.on_going_order.presentation.screens.OnGoingOrderScreen
import com.sendiko.calcmenus.employee.order_resume.presentation.EmployeeOrderResumeViewModel
import com.sendiko.calcmenus.employee.order_resume.presentation.OrderResumeScreen
import com.sendiko.calcmenus.employee.order_resume.presentation.OrderResumeScreenEvent
import com.sendiko.calcmenus.employee.post_screen.presentation.PostDeliverScreen
import com.sendiko.calcmenus.employee.post_screen.presentation.PostOrderResumeScreen
import com.sendiko.calcmenus.employee.post_screen.presentation.PostPayedScreen
import com.sendiko.calcmenus.employee.profile.presentation.EmployeeProfileViewModel
import com.sendiko.calcmenus.resto.auth.login.presentation.RestoLoginScreen
import com.sendiko.calcmenus.resto.auth.login.presentation.RestoLoginViewModel
import com.sendiko.calcmenus.resto.auth.register.presentation.RestoRegisterScreen
import com.sendiko.calcmenus.resto.auth.register.presentation.RestoRegisterViewModel
import com.sendiko.calcmenus.resto.main.dashboard.presentation.DashboardScreen
import com.sendiko.calcmenus.resto.main.employee.presentation.screens.CreateEmployeeScreen
import com.sendiko.calcmenus.resto.main.employee.presentation.screens.ViewEmployeeScreen
import com.sendiko.calcmenus.resto.main.menu.presentation.CreateMenuScreen
import com.sendiko.calcmenus.resto.main.menu.presentation.EditMenuScreen
import com.sendiko.calcmenus.resto.profile.presentation.RestoProfileViewModel
import com.sendiko.calcmenus.splash.presentation.SplashScreen
import com.sendiko.calcmenus.splash.presentation.SplashScreenViewModel
import com.sendiko.calcmenus.welcome.presentation.WelcomeResto
import com.sendiko.calcmenus.welcome.presentation.WelcomeScreen
import com.sendiko.calcmenus.welcome.presentation.WelcomeScreenEvents
import dagger.hilt.android.AndroidEntryPoint
import com.sendiko.calcmenus.employee.profile.presentation.ProfileScreen as EmployeeProfileScreen
import com.sendiko.calcmenus.resto.profile.presentation.ProfileScreen as RestoProfileScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        WindowCompat.setDecorFitsSystemWindows(
            window,
            false
        )
        var tempList = emptyList<MenusItem>()

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
                            else -> false
                        }
                        NavHost(
                            navController = navController,
                            startDestination = Graphs.WholeGraphRoute.graph,
                            enterTransition = { fadeIn() },
                            exitTransition = { fadeOut() },
                            builder = {
                                navigation(
                                    route = Graphs.WholeGraphRoute.graph,
                                    startDestination = Routes.SplashScreenRoute.route,
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
                                        composable(
                                            route = Routes.SplashScreenRoute.route,
                                            content = {
                                                val viewModel = hiltViewModel<SplashScreenViewModel>()
                                                SplashScreen(
                                                    state = viewModel.state.collectAsState().value,
                                                    navController = navController
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
                                                        val viewModel = hiltViewModel<RestoLoginViewModel>()
                                                        RestoLoginScreen(
                                                            state = viewModel.state.collectAsState().value,
                                                            onEvent = viewModel::onEvent,
                                                            navController = navController
                                                        )
                                                    }
                                                )
                                                composable(
                                                    route = Routes.RestoRegister.route,
                                                    content = {
                                                        val viewModel = hiltViewModel<RestoRegisterViewModel>()
                                                        RestoRegisterScreen(
                                                            state = viewModel.state.collectAsState().value,
                                                            onEvent = viewModel::onEvent,
                                                            navController = navController
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
                                                        val viewModel = hiltViewModel<EmployeeLoginViewModel>()
                                                        EmployeeLoginScreen(
                                                            state = viewModel.state.collectAsState().value,
                                                            onEvents = viewModel::onEvent,
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
                                                        val viewModel = hiltViewModel<RestoProfileViewModel>()
                                                        RestoProfileScreen(
                                                            state = viewModel.state.collectAsState().value,
                                                            onEvent = viewModel::onEvent,
                                                            navController = navController,
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
                                                        val viewModel = hiltViewModel<EmployeeProfileViewModel>()
                                                        EmployeeProfileScreen(
                                                            state = viewModel.state.collectAsState().value,
                                                            onEvent = viewModel::onEvent,
                                                            navController = navController,
                                                            onNavigateBack = { route ->
                                                                navController.navigate(route)
                                                            }
                                                        )
                                                    }
                                                )
                                                composable(
                                                    route = Routes.EmployeeMenuScreen.route,
                                                    content = {
                                                        val viewModel = hiltViewModel<EmployeeMenuViewModel>()
                                                        val list = viewModel.state.collectAsState().value.orderedMenuList
                                                        LaunchedEffect(
                                                            key1 = list,
                                                            block = {
                                                                if (list == emptyList<MenusItem>())
                                                                    viewModel.onEvent(
                                                                        MenuScreenEvent.OnLoadMenuList(tempList))
                                                            }
                                                        )
                                                        MenuScreen(
                                                            state = viewModel.state.collectAsState().value,
                                                            onEvent = viewModel::onEvent,
                                                            onNavigate = { route, listt ->
                                                                navController.navigate(route)
                                                                tempList = emptyList()
                                                                tempList = listt
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
                                                        val viewModel = hiltViewModel<EmployeeOrderResumeViewModel>()
                                                        val list = viewModel.state.collectAsState().value.orderedMenuList
                                                        LaunchedEffect(
                                                            key1 = list,
                                                            block = {
                                                                if (list == emptyList<MenusItem>())
                                                                    viewModel.onEvent(   OrderResumeScreenEvent.OnLoadList(tempList))
                                                            }
                                                        )
                                                        OrderResumeScreen(
                                                            onAddMoreMenu = { route, listt ->
                                                                navController.navigate(route)
                                                                tempList = emptyList()
                                                                tempList = listt
                                                            },
                                                            onPlaceOrder = {
                                                                navController.navigate(Routes.EmployeePostOrderScreen.route)
                                                            },
                                                            onEvent = viewModel::onEvent,
                                                            state = viewModel.state.collectAsState().value
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
                        )
                    }
                }
            )
        }
    }
}