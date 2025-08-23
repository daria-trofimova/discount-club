package com.example.discountclub.profile

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ProfileScreen(
    onNavigateToRegistration: () -> Unit,
    onNavigateToMyPurchases: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = viewModel(),
    ) {
    GuestProfileScreen(onRegistrationButtonClicked = onNavigateToRegistration)
}

@Composable
private fun GuestProfileScreen(
    onRegistrationButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(onClick = onRegistrationButtonClicked) {
        Text(text = "Регистрация для клиентов банка")
    }
}

@Composable
private fun ProfileScreen(
    name: String,
    lastName: String,
    onMyPurchasesButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(onClick = onMyPurchasesButtonClicked) {
        Text(text = "Мои покупки")
    }
}