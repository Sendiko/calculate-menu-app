package com.sendiko.calcmenus.ui.screens.employee.menu_screen

sealed interface MenuScreenEvent {
    data class OnSearchInput(val menuItem: String): MenuScreenEvent
    data class OnAddMenuToList(val menu: String): MenuScreenEvent
    data class OnRemoveMenuFromList(val menu: String): MenuScreenEvent
    data class OnSortToggle(val sortBy: String): MenuScreenEvent
    data class RequestMenuData(val token: String): MenuScreenEvent
    data class SwitchPages(val page: Int): MenuScreenEvent
    object OnPlaceOrder: MenuScreenEvent
}