package com.sendiko.calcmenus.employee.login.presentation

sealed interface EmployeeLoginEvents {
    data class OnEmailInput(val email: String): EmployeeLoginEvents
    data class OnPasswordInput(val password: String): EmployeeLoginEvents
    data class PasswordVisibilityToggle(val isVisible: Boolean): EmployeeLoginEvents
    object OnClickLogin: EmployeeLoginEvents
}