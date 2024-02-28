package com.sendiko.calcmenus.resto.auth.login.presentation

sealed interface RestoLoginScreenEvent {
    data class OnEmailInput(val email: String): RestoLoginScreenEvent
    data class OnPasswordInput(val password: String): RestoLoginScreenEvent
    data class OnPasswordVisibilityToggle(val isVisible: Boolean): RestoLoginScreenEvent
    object OnLoginClick: RestoLoginScreenEvent
}