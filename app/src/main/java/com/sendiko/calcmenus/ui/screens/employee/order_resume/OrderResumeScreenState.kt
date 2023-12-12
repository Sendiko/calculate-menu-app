package com.sendiko.calcmenus.ui.screens.employee.order_resume

import com.sendiko.calcmenus.remote.responses.MenusItem
import com.sendiko.calcmenus.ui.utils.FailedState

data class OrderResumeScreenState(
    val orderedMenuList: List<MenusItem> = emptyList(),
    val orderedMenuNoteList: List<String> = emptyList(),
    val tableName: String = "",
    val isLoading: Boolean = false,
    val failedState: FailedState = FailedState(isFailed = false, failedMessage = null),
)
