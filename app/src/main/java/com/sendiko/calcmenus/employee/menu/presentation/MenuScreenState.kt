package com.sendiko.calcmenus.employee.menu.presentation

import com.sendiko.calcmenus.employee.menu.data.MenusItem
import com.sendiko.calcmenus.ui.utils.FailedState

data class MenuScreenState(
    val orderedMenuList: List<MenusItem> = emptyList(),
    val orderedMenuNoteList: List<String> = emptyList(),
    val menuList: List<MenusItem?>? = emptyList(),
    val currentPage: Int = 0,
    val sortBy: String = "desc",
    val searchText: String = "",
    val name: String = "",
    val token: String = "",
    val isLoading: Boolean = false,
    val failedState: FailedState = FailedState(),
    val restoId: String = "",
)
