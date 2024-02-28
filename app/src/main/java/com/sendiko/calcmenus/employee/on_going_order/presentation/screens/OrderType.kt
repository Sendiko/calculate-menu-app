package com.sendiko.calcmenus.employee.on_going_order.presentation.screens

data class OrderType(
    val isPayed: Boolean = false,
    val isDelivered: Boolean = false,
    val isSelected: Boolean,
)
