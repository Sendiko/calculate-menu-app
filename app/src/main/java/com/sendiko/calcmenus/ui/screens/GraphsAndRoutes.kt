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
    object RestoDashboardScreen: Routes("resto_dashboard")
    object RestoCreateMenuScreen: Routes("resto_create_menu")
    object RestoEditMenuScreen: Routes("resto_edit_menu")
    object RestoCreateEmployeeScreen: Routes("resto_create_employee")
    object RestoViewEmployeeScreen: Routes("resto_view_employee")
    object EmployeeLogin: Routes("emp_login")
    object EmployeeProfileScreen: Routes("emp_profile")
    object EmployeeMenuScreen: Routes("emp_menu_screen")
    object EmployeeOngoingOrdersScreen: Routes("emp_ongoing_orders")
    object EmployeeOrderResumeScreen: Routes("emp_order_resume")
    object EmployeePostOrderScreen: Routes("emp_post_order")
    object EmployeePostDeliverScreen: Routes("emp_post_deliver")
    object EmployeePostPayedScreen: Routes("emp_post_payed")
}
