package com.example.tipcalculator.presentation.screen.tipScreen

data class TipState(
    val billAmount: String = "",
    val tipPercent: Int = 0,
    val split: Int = 1,
    val total: String = "",
    val perPerson: String = ""
)
