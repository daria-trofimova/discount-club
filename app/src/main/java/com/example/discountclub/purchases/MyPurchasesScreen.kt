package com.example.discountclub.purchases

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.discountclub.domain.model.Purchase
import java.util.Date

@Composable
fun MyPurchasesScreen(
    modifier: Modifier = Modifier,
    viewModel: MyPurchasesViewModel = viewModel(),
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
fun LoadingMyPurchases(
    modifier: Modifier = Modifier,
) {
    Text(text = "Loading")
}

@Composable
private fun MyPurchases(
    purchasesByDate: Map<Date, List<Purchase>>,
    modifier: Modifier = Modifier,
) {

}