package com.sendiko.calcmenus.ui.screens.restaurant.auth.login

sealed interface RestoLoginScreenEvent {
    data class OnEmailInput(val email: String): RestoLoginScreenEvent
    data class OnPasswordInput(val password: String): RestoLoginScreenEvent
    data class OnPasswordVisibilityToggle(val isVisible: Boolean): RestoLoginScreenEvent
    object OnLoginClick: RestoLoginScreenEvent
}