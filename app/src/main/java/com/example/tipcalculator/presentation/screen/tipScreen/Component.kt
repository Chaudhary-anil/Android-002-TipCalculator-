package com.example.tipcalculator.presentation.screen.tipScreen

import java.text.NumberFormat

fun formatNpr(amount: Double): String {
    val locale = java.util.Locale("en", "NP")
    val formatter = NumberFormat.getCurrencyInstance(locale)

    return formatter.format(amount).replace("NPR", "Rs.")
}