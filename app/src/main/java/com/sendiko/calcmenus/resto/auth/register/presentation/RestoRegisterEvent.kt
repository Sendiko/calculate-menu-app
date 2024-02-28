package com.sendiko.calcmenus.resto.auth.register.presentation

sealed interface RestoRegisterEvent {
    data class OnRestoNameInput(val restoName: String): RestoRegisterEvent
    data class OnRestoAddressInput(val restoAddress: String): RestoRegisterEvent
    data class OnRestoPhoneInput(val restoPhone: String): RestoRegisterEvent
    data class OnEmailInput(val email: String): RestoRegisterEvent
    data class OnPasswordInput(val password: String): RestoRegisterEvent
    data class OnNavigatePart(val page: Int): RestoRegisterEvent
    data class OnPasswordVisibilityToggle(val isVisible: Boolean): RestoRegisterEvent
    object OnClickRegister: RestoRegisterEvent
}