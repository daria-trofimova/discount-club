package com.example.discountclub.profile

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ProfileScreen(
    onNavigateToRegistration: () -> Unit,
    onNavigateToMyPurchases: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = viewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    when (val currentUiState = uiState.value) {
        is ProfileUiState.Loading -> LoadingProfile()
        is ProfileUiState.Unauthenticated ->
            GuestProfile(onRegistrationButtonClicked = onNavigateToRegistration)

        is ProfileUiState.Authenticated -> Profile(
            name = currentUiState.name,
            lastName = currentUiState.lastName,
            onMyPurchasesButtonClicked = onNavigateToMyPurchases,
        )
    }
}

@Composable
private fun LoadingProfile(
    modifier: Modifier = Modifier,
) {

}

@Composable
private fun GuestProfile(
    onRegistrationButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(onClick = onRegistrationButtonClicked) {
        Text(text = "Регистрация для клиентов банка")
    }
}

@Composable
private fun Profile(
    name: String,
    lastName: String,
    onMyPurchasesButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(onClick = onMyPurchasesButtonClicked) {
        Text(text = "Мои покупки")
    }
}