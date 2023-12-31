package com.sendiko.calcmenus

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.sendiko.calcmenus.repository.EmployeeRepository
import com.sendiko.calcmenus.repository.RestoRepository
import com.sendiko.calcmenus.repository.preferences.AppPreferences
import com.sendiko.calcmenus.repository.viewmodels.SplashScreenViewModel
import com.sendiko.calcmenus.repository.viewmodels.ViewModelFactory
import com.sendiko.calcmenus.repository.viewmodels.employee.EmployeeLoginViewModel
import com.sendiko.calcmenus.repository.viewmodels.employee.EmployeeMenuViewModel
import com.sendiko.calcmenus.repository.viewmodels.employee.EmployeeProfileViewModel
import com.sendiko.calcmenus.repository.viewmodels.resto.RestoLoginViewModel
import com.sendiko.calcmenus.repository.viewmodels.resto.RestoProfileViewModel
import com.sendiko.calcmenus.repository.viewmodels.resto.RestoRegisterViewModel
import com.sendiko.calcmenus.ui.screens.Graphs
import com.sendiko.calcmenus.ui.screens.Routes
import com.sendiko.calcmenus.ui.screens.employee.login_screen.EmployeeLoginScreen
import com.sendiko.calcmenus.ui.screens.employee.menu_screen.MenuScreen
import com.sendiko.calcmenus.ui.screens.employee.ongoing_order.OnGoingOrderScreen
import com.sendiko.calcmenus.ui.screens.employee.order_resume.OrderResumeScreen
import com.sendiko.calcmenus.ui.screens.employee.post_screen.PostDeliverScreen
import com.sendiko.calcmenus.ui.screens.employee.post_screen.PostOrderResumeScreen
import com.sendiko.calcmenus.ui.screens.employee.post_screen.PostPayedScreen
import com.sendiko.calcmenus.ui.screens.restaurant.WelcomeResto
import com.sendiko.calcmenus.ui.screens.restaurant.auth.login.RestoLoginScreen
import com.sendiko.calcmenus.ui.screens.restaurant.auth.register.RestoRegisterScreen
import com.sendiko.calcmenus.ui.screens.restaurant.main.DashboardScreen
import com.sendiko.calcmenus.ui.screens.restaurant.main.employee.CreateEmployeeScreen
import com.sendiko.calcmenus.ui.screens.restaurant.main.employee.ViewEmployeeScreen
import com.sendiko.calcmenus.ui.screens.restaurant.main.menu.CreateMenuScreen
import com.sendiko.calcmenus.ui.screens.restaurant.main.menu.EditMenuScreen
import com.sendiko.calcmenus.ui.screens.welcome.SplashScreen
import com.sendiko.calcmenus.ui.screens.welcome.WelcomeScreen
import com.sendiko.calcmenus.ui.screens.welcome.WelcomeScreenEvents
import com.sendiko.calcmenus.ui.theme.CalcMenusTheme
import com.sendiko.calcmenus.ui.theme.NotWhite
import com.sendiko.calcmenus.ui.theme.PrimaryRed
import com.sendiko.calcmenus.ui.utils.dataStore
import com.sendiko.calcmenus.ui.screens.employee.profile.ProfileScreen as EmployeeProfileScreen
import com.sendiko.calcmenus.ui.screens.restaurant.profile.ProfileScreen as RestoProfileScreen

class MainActivity : ComponentActivity() {

    private val appPreferences by lazy {
        AppPreferences.getInstance(requireNotNull(applicationContext).dataStore)
    }

    private fun <T : ViewModel> obtainViewModel(
        appPreferences: AppPreferences,
        modelClass: Class<T>
    ): T {
        val factory = ViewModelFactory.getInstance(appPreferences)
        return ViewModelProvider(this, factory)[modelClass]
    }

    private val splashScreenViewModel by lazy {
        obtainViewModel(appPreferences, SplashScreenViewModel::class.java)
    }

    private val restoLoginViewModel by lazy {
        obtainViewModel(appPreferences, RestoLoginViewModel::class.java)
    }

    private val employeeLoginViewModel by lazy {
        obtainViewModel(appPreferences, EmployeeLoginViewModel::class.java)
    }

    private val restoProfileLoginViewModel by lazy {
        obtainViewModel(appPreferences, RestoProfileViewModel::class.java)
    }

    private val employeeProfileViewModel by lazy {
        obtainViewModel(appPreferences, EmployeeProfileViewModel::class.java)
    }

    private val employeeMenuViewModel by lazy {
        obtainViewModel(appPreferences, EmployeeMenuViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        WindowCompat.setDecorFitsSystemWindows(
            window,
            false
        )
        val restoRepository = RestoRepository()
        val employeeRepository = EmployeeRepository()

        val restoRegisterViewModel = RestoRegisterViewModel(restoRepository)

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
                                                SplashScreen(
                                                    state = splashScreenViewModel.state.collectAsState().value,
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
                                                        RestoLoginScreen(
                                                            state = restoLoginViewModel.state.collectAsState().value,
                                                            onEvent = restoLoginViewModel::onEvent,
                                                            navController = navController
                                                        )
                                                    }
                                                )
                                                composable(
                                                    route = Routes.RestoRegister.route,
                                                    content = {
                                                        RestoRegisterScreen(
                                                            state = restoRegisterViewModel.state.collectAsState().value,
                                                            onEvent = restoRegisterViewModel::onEvent,
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
                                                            state = restoProfileLoginViewModel.state.collectAsState().value,
                                                            onEvent = restoProfileLoginViewModel::onEvent,
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
                                                        EmployeeProfileScreen(
                                                            state = employeeProfileViewModel.state.collectAsState().value,
                                                            onEvent = employeeProfileViewModel::onEvent,
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
                                                        MenuScreen(
                                                            state = employeeMenuViewModel.state.collectAsState().value,
                                                            onEvent = employeeMenuViewModel::onEvent,
                                                            onNavigate = { route ->
                                                                navController.navigate(route )
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
                        )
                    }
                }
            )
        }
    }
}