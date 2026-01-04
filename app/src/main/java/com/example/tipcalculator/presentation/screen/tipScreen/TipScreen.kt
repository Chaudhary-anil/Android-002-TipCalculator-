package com.example.tipcalculator.presentation.screen.tipScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.tipcalculator.R
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipScreenUi(viewModel: TipCalculatorViewModel = hiltViewModel()) {
    val state = viewModel.state
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.app_title), style = MaterialTheme.typography.headlineLarge) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { viewModel.onEvent(TipEvent.Reset) }
            ) {
                Icon(
                    imageVector = Icons.Default.RestartAlt,
                    contentDescription = "Reset"
                )
                Text(stringResource(R.string.reset), style = MaterialTheme.typography.bodyMedium)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.billAmount)
            )
            OutlinedTextField(
                value = state.billAmount,
                onValueChange = { viewModel.onEvent(event = TipEvent.BillAmountChanged(amount = it) )},
                label = { Text(stringResource(R.string.bill_label))},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Text(stringResource(R.string.tip_percent, state.tipPercent))
            Slider(
                value = state.tipPercent.toFloat(),
                onValueChange = { viewModel.onEvent(TipEvent.TipPercentChanged(percent = it.roundToInt()))},
                valueRange = 0f..30f,
                steps = 29
            )

            Text(stringResource(R.string.split, state.split))
            Slider(
                value = state.split.toFloat(),
                onValueChange = { viewModel.onEvent(TipEvent.SplitChanged(it.roundToInt())) },
                valueRange = 1f..16f,
                steps = 14
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(stringResource(R.string.total, state.total), style = MaterialTheme.typography.bodyLarge)
                    Text(stringResource(R.string.per_person, state.perPerson), style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}





























