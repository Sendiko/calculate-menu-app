package com.sendiko.calcmenus.ui.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import java.text.NumberFormat
import java.util.Currency

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_preferences")

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
