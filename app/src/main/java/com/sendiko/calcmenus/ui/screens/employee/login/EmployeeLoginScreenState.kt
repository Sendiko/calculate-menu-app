package com.sendiko.calcmenus.ui.screens.employee.login

import com.sendiko.calcmenus.ui.utils.FailedState

data class EmployeeLoginScreenState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val failedState: FailedState = FailedState(),
    val isPasswordVisible: Boolean = false,
    val loginSuccessful: Boolean = false,
)