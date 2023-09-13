package com.sendiko.calcmenus.ui.screens.employee.ongoing_order

object OrderTypeList {
    val orderTypeList = listOf(
        OrderType(
            isPayed = false,
            isDelivered = false,
            isSelected = true
        ),
        OrderType(
            isPayed = false,
            isDelivered = true,
            isSelected = true
        ),
    )
}