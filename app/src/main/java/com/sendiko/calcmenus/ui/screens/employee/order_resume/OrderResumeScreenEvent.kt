package com.sendiko.calcmenus.ui.screens.employee.order_resume

import com.sendiko.calcmenus.remote.responses.MenusItem

sealed interface OrderResumeScreenEvent{
    data class OnLoadList(val menuList: List<MenusItem>): OrderResumeScreenEvent
    data class OnAddMenu(val menu: MenusItem): OrderResumeScreenEvent
    data class OnRemoveMenu(val menu: MenusItem): OrderResumeScreenEvent
    data class OnAddNotes(val note: String): OrderResumeScreenEvent
    data class OnInputTableName(val tableName: String): OrderResumeScreenEvent
    object OnClearMenu: OrderResumeScreenEvent
    object OnPlaceOrder: OrderResumeScreenEvent
}