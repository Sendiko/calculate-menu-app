package com.sendiko.calcmenus.welcome.presentation

sealed interface WelcomeScreenEvents {
    data class OnNavigate(val route: String): WelcomeScreenEvents
}