package com.sendiko.calcmenus.resto.auth.register.presentation

import com.sendiko.calcmenus.ui.utils.FailedState

data class RestoRegisterState(
    val restoName: String = "",
    val restoAddress: String = "",
    val restoPhone: String = "",
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val failedState: FailedState = FailedState(),
    val isPasswordVisible: Boolean = false,
    val loginSuccessful: Boolean = false,
    val registerPart: Int = 1,
)