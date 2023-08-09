package com.sendiko.calcmenus.ui.screens

sealed class Graphs(val graph: String){
    object MainGraph: Graphs("main_graph")
    object RestoAuthGraph: Graphs("resto_auth_graph")
    object EmpAuthGraph: Graphs("emp_auth_graph")
}

sealed class Routes(val route: String){
    object WelcomeScreenRoute: Routes("welcome_screen")
    object RestoWelcome: Routes("resto_welcome")
    object EmployeeLogin: Routes("emp_login")
    object RestoLogin: Routes("resto_login")
    object RestoRegister: Routes("resto_register")
}
