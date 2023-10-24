package com.sendiko.calcmenus.ui.utils

sealed class LoginState(val account: String){
    object EmployeeAccount: LoginState("employee_account")
    object RestaurantAccount: LoginState("restaurant_account")
}
