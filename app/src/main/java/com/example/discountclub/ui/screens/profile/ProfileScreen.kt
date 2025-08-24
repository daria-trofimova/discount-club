package com.example.discountclub.ui.screens.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.discountclub.R
import com.example.discountclub.domain.model.Language
import com.example.discountclub.domain.model.Settings
import com.example.discountclub.domain.model.User

@Composable
fun ProfileScreen(
    onNavigateToRegistration: () -> Unit,
    onNavigateToMyPurchases: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    when (val currentUiState = uiState.value) {
        is ProfileUiState.Loading -> LoadingProfile(modifier = modifier)
        is ProfileUiState.UnAuthenticated -> {}
        is ProfileUiState.Authenticated -> Profile(
            user = currentUiState.user,
            settings = currentUiState.settings,
            onMyPurchasesButtonClicked = onNavigateToMyPurchases,
            onRegistrationButtonClicked = onNavigateToRegistration,
            modifier = modifier,
        )
    }
}

@Composable
private fun LoadingProfile(
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
private fun Profile(
    user: User,
    settings: Settings,
    onMyPurchasesButtonClicked: () -> Unit,
    onRegistrationButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        if (user.name != null && user.lastName != null) {
            UserFullName(name = user.name, lastName = user.lastName)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = user.phoneNumber,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(24.dp))

        ProfileSetting(
            title = stringResource(R.string.my_purchases),
            onClick = onMyPurchasesButtonClicked,
        )
        Spacer(modifier = Modifier.height(24.dp))
        Spacer(modifier = Modifier.height(12.dp))
        ProfileSettings(
            email = user.email,
            isBiometricAllowed = settings.isBiometricAuthAllowed,
            language = settings.language,
            onRegistrationButtonClicked = onRegistrationButtonClicked,
        )
    }
}

@Composable
private fun UserFullName(
    name: String,
    lastName: String,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.Bottom) {
        Column {
            Text(
                text = name,
                fontSize = 20.sp
            )
            Text(
                text = lastName,
                fontSize = 20.sp
            )
        }
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = stringResource(R.string.edit_first_and_last_name),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
private fun ProfileSettings(
    email: String?,
    isBiometricAllowed: Boolean,
    language: Language,
    onRegistrationButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.settings),
            fontSize = 14.sp,
        )
        ProfileSetting(
            title = stringResource(R.string.email),
            subtitle = email ?: "",
            onClick = { }
        )
        Spacer(modifier = Modifier.height(1.dp))
        var tempIsBiometricAllowed by remember { mutableStateOf(isBiometricAllowed) }
        ProfileSetting(
            title = stringResource(R.string.login_by_biometrics),
            contentEnd = {
                Switch(
                    checked = tempIsBiometricAllowed,
                    onCheckedChange = { tempIsBiometricAllowed = it },
                )
            },
            onClick = { },
        )
        Spacer(modifier = Modifier.height(1.dp))
        ProfileSetting(
            title = stringResource(R.string.change_4_digit_code),
            onClick = { }
        )
        Spacer(modifier = Modifier.height(1.dp))
        ProfileSetting(
            title = stringResource(R.string.registration_for_bank_clients),
            onClick = onRegistrationButtonClicked
        )
        Spacer(modifier = Modifier.height(1.dp))
        ProfileSetting(
            title = stringResource(R.string.language),
            subtitle = language.name,
            onClick = { }
        )
        Spacer(modifier = Modifier.height(1.dp))
    }
}

@Preview
@Composable
private fun ProfilePreview() {
    Profile(
        user = User(
            phoneNumber = "+15550000000",
            name = "John",
            lastName = "Doe",
            email = "example@mail.com",
        ),
        settings = Settings(
            isBiometricAuthAllowed = true,
            language = Language.RUSSIAN,
        ),
        onMyPurchasesButtonClicked = {},
        onRegistrationButtonClicked = {},
    )
}
