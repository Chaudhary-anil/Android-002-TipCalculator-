package com.example.tipcalculator.presentation.screen.tipScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.NumberFormat
import javax.inject.Inject

@HiltViewModel
class TipCalculatorViewModel @Inject constructor(): ViewModel() {

    var state by mutableStateOf(TipState())
        private set

    init {
        calculate()
    }

    fun onEvent(event: TipEvent) {
        when(event) {
            is TipEvent.BillAmountChanged -> {
               state = state.copy(billAmount = event.amount)
            }
            TipEvent.Reset -> reset()
            is TipEvent.SplitChanged -> {
                state = state.copy(split = event.splitNumber)
            }
            is TipEvent.TipPercentChanged -> {
                state = state.copy(tipPercent = event.percent)
            }
        }
        calculate()
    }

    private fun calculate() {
        val bill = state.billAmount.toDoubleOrNull() ?: 0.0
        val tip = state.tipPercent * bill / 100.0
        val total = bill + tip
        val perPerson = total / state.split

//        val format = NumberFormat.getCurrencyInstance()
        state = state.copy(
            total = formatNpr(amount = total),
            perPerson = formatNpr(amount = perPerson)
        )
    }

    private fun reset() {
        viewModelScope.launch {
            state = state.copy(
                billAmount = "",
                tipPercent = 0,
                split = 1,
                total = "",
                perPerson = ""
            )
        }
    }
}































































