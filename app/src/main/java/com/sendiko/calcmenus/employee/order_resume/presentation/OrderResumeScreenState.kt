package com.sendiko.calcmenus.employee.order_resume.presentation

import com.sendiko.calcmenus.employee.menu.data.MenusItem
import com.sendiko.calcmenus.ui.utils.FailedState

data class OrderResumeScreenState(
    val orderedMenuList: List<MenusItem> = emptyList(),
    val orderedMenuNoteList: List<String> = emptyList(),
    val tableName: String = "",
    val isLoading: Boolean = false,
    val failedState: FailedState = FailedState(isFailed = false, failedMessage = null),
    val token: String = "",
    val isSuccess: Boolean = false,
)
