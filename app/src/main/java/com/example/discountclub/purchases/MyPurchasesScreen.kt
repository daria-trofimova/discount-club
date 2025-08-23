package com.example.discountclub.purchases

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.discountclub.domain.model.DatePurchases

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
    Text(text = "Loading")
}

@Composable
private fun MyPurchases(
    purchasesByDate: List<DatePurchases>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        item {
            Text(text = "My purchases")
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
    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = datePurchases.date.toString())
        Column {
            datePurchases.items.forEach { item ->
                key(item.name) {
                    Text(text = item.name)
                }
            }
        }
    }
}