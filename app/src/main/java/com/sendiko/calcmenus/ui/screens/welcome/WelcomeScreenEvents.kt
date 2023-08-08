package com.sendiko.calcmenus.ui.screens.welcome

sealed interface WelcomeScreenEvents {
    data class OnNavigate(val route: String): WelcomeScreenEvents
}