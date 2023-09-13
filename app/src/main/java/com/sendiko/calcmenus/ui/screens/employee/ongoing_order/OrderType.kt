package com.sendiko.calcmenus.ui.screens.employee.ongoing_order

data class OrderType(
    val isPayed: Boolean = false,
    val isDelivered: Boolean = false,
    val isSelected: Boolean,
)
