package com.example.tipcalculator.presentation.screen.tipScreen

sealed interface TipEvent {
    data class BillAmountChanged(val amount: String) : TipEvent
    data class TipPercentChanged(val percent: Int) : TipEvent
    data class SplitChanged(val splitNumber: Int): TipEvent
    object Reset: TipEvent
}