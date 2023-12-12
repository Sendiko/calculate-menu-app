package com.sendiko.calcmenus.ui.screens.employee.menu_screen

import com.sendiko.calcmenus.remote.responses.MenusItem

sealed interface MenuScreenEvent {
    data class OnSearchInput(val menuItem: String): MenuScreenEvent
    data class OnAddMenuToList(val menu: MenusItem): MenuScreenEvent
    data class OnRemoveMenuFromList(val menu: MenusItem): MenuScreenEvent
    data class OnSortToggle(val sortBy: String): MenuScreenEvent
    data class RequestMenuData(val token: String): MenuScreenEvent
    data class SwitchPages(val page: Int): MenuScreenEvent
    data class OnLoadMenuList(val menuList: List<MenusItem>): MenuScreenEvent
    object OnPlaceOrder: MenuScreenEvent
}