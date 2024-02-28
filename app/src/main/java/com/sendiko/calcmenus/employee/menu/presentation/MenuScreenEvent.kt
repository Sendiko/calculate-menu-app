package com.sendiko.calcmenus.employee.menu.presentation

import com.sendiko.calcmenus.employee.menu.data.MenusItem

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