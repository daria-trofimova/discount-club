package com.example.discountclub.purchases

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.discountclub.R
import com.example.discountclub.domain.model.DatePurchases
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun MyPurchasesScreen(
    modifier: Modifier = Modifier,
    viewModel: MyPurchasesViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    when (val state = uiState.value) {
        is MyPurchasesUiState.Loading -> LoadingMyPurchases()
        is MyPurchasesUiState.Success -> MyPurchases(
            purchasesByDate = state.purchasesByDate,
            modifier = modifier,
        )
    }
}

@Composable
private fun LoadingMyPurchases(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun MyPurchases(
    purchasesByDate: List<DatePurchases>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.my_purchases),
                style = MaterialTheme.typography.headlineSmall,
            )
        }
        items(items = purchasesByDate, key = { it.date.time }) { datePurchases ->
            DatePurchases(datePurchases = datePurchases)
        }
    }
}

@Composable
private fun DatePurchases(
    datePurchases: DatePurchases,
    modifier: Modifier = Modifier,
) {
    val dateFormatter = remember { SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()) }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Text(
            text = dateFormatter.format(datePurchases.date),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Column {
            datePurchases.items.forEach { item ->
                key(item.name) {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}