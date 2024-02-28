package com.sendiko.calcmenus.employee.on_going_order.presentation.screens

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