package com.sendiko.calcmenus.ui.screens

sealed class Graphs(val graph: String){
    object EmpMainGraph: Graphs("emp_main_graph")
    object EmpAuthGraph: Graphs("emp_auth_graph")
    object RestoMainGraph: Graphs("resto_main_graph")
    object RestoAuthGraph: Graphs("resto_auth_graph")
}

sealed class Routes(val route: String){
    object WelcomeScreenRoute: Routes("welcome_screen")
    object RestoWelcome: Routes("resto_welcome")
    object RestoLogin: Routes("resto_login")
    object RestoRegister: Routes("resto_register")
    object EmployeeLogin: Routes("emp_login")
    object EmployeeMenuScreen: Routes("emp_menu_screen")
}
