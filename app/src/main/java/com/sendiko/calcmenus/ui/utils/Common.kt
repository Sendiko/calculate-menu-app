package com.sendiko.calcmenus.ui.utils

import java.text.NumberFormat
import java.util.Currency

fun formatCurrency(amount: String): String {
    val locale = java.util.Locale("id", "ID") // Indonesian locale
    val numberFormat = NumberFormat.getCurrencyInstance(locale)
    numberFormat.currency = Currency.getInstance("IDR")

    val amountValue = try {
        amount.toDouble()
    } catch (e: NumberFormatException) {
        0.0
    }

    return numberFormat.format(amountValue)
}
