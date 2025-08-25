package com.example.discountclub.ui.screens.purchases

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.discountclub.domain.model.DatePurchases
import com.example.discountclub.domain.model.PurchaseItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun MyPurchasesScreen(
    modifier: Modifier = Modifier,
    viewModel: MyPurchasesViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    when (val state = uiState.value) {
        is MyPurchasesUiState.Loading -> LoadingMyPurchases(modifier = modifier)
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

// TODO: Optimize for long, nested lists
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
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        PurchaseDate(date = datePurchases.date)
        Column {
            datePurchases.items.forEach { item ->
                key(item.name) {
                    PurchaseItem(item)
                }
            }
        }
    }
}

@Composable
private fun PurchaseDate(
    date: Date,
    modifier: Modifier = Modifier,
) {
    val dateFormatter = remember { SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()) }
    Text(
        text = dateFormatter.format(date),
        style = MaterialTheme.typography.titleSmall,
        textAlign = TextAlign.Center,
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
private fun PurchaseItem(
    item: PurchaseItem,
    modifier: Modifier = Modifier,
) {
    Text(
        text = item.name,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Center,
        modifier = modifier.fillMaxWidth()
    )
}
