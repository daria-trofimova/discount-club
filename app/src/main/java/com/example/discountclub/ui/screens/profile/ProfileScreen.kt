package com.example.discountclub.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.discountclub.R
import com.example.discountclub.domain.model.Language
import com.example.discountclub.domain.model.Settings
import com.example.discountclub.domain.model.User
import com.example.discountclub.ui.components.DiscountClubSwitch
import com.example.discountclub.ui.components.LoadingOverlay

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
            onMyPurchasesButtonClick = onNavigateToMyPurchases,
            onRegistrationButtonClick = onNavigateToRegistration,
            modifier = modifier,
        )
    }
}

@Composable
private fun LoadingProfile(
    modifier: Modifier = Modifier,
) {
    LoadingOverlay(modifier = modifier)
}

@Composable
private fun Profile(
    user: User,
    settings: Settings,
    onMyPurchasesButtonClick: () -> Unit,
    onRegistrationButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        ProfileInfo(
            name = user.name,
            lastName = user.lastName,
            phoneNumber = user.phoneNumber,
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = stringResource(R.string.my_purchases),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.height(12.dp))
        MyPurchasesProfileSetting(onClick = onMyPurchasesButtonClick)
        Spacer(modifier = Modifier.height(32.dp))
        ProfileSettings(
            email = user.email,
            isEmailConfirmed = user.isEmailConfirmed,
            isBiometricAllowed = settings.isBiometricAuthAllowed,
            language = settings.language,
            onRegistrationButtonClick = onRegistrationButtonClick,
        )
    }
}

@Composable
fun ProfileInfo(
    name: String?,
    lastName: String?,
    phoneNumber: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        UserFullName(name = name, lastName = lastName)
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = phoneNumber,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun UserFullName(
    name: String?,
    lastName: String?,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = name ?: stringResource(R.string.name),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = lastName ?: stringResource(R.string.last_name),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.width(12.dp))
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = stringResource(R.string.edit_first_and_last_name),
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { }
            )
        }
    }
}

@Composable
private fun ProfileSettings(
    email: String?,
    isEmailConfirmed: Boolean,
    isBiometricAllowed: Boolean,
    language: Language,
    onRegistrationButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.settings),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.height(12.dp))
        EmailProfileSetting(
            email = email,
            isEmailConfirmed = isEmailConfirmed,
        )
        Spacer(modifier = Modifier.height(12.dp))
        BiometricProfileSetting(
            isBiometricAllowed = isBiometricAllowed,
        )
        Spacer(modifier = Modifier.height(12.dp))
        ProfileSetting(
            onClick = { },
            title = stringResource(R.string.change_4_digit_code)
        )
        Spacer(modifier = Modifier.height(12.dp))
        ProfileSetting(
            onClick = onRegistrationButtonClick,
            title = stringResource(R.string.registration_for_bank_clients)
        )
        Spacer(modifier = Modifier.height(12.dp))
        ProfileSetting(
            onClick = { },
            subtitle = language.asString(),
            title = stringResource(R.string.language)
        )
    }
}

@Composable
fun MyPurchasesProfileSetting(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ProfileSetting(
        onClick = onClick,
        modifier = modifier,
        title = null,
        contentStart = {
            Spacer(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        MaterialTheme.colorScheme.primaryContainer,
                        CircleShape
                    ),
            )
        }
    )
}

@Composable
fun EmailProfileSetting(
    email: String?,
    isEmailConfirmed: Boolean,
    modifier: Modifier = Modifier,
) {
    ProfileSetting(
        onClick = { },
        modifier = modifier,
        title = stringResource(R.string.email),
        contentStart = {
            if (email != null) {
                Column {
                    Subtitle(text = email)
                    if (!isEmailConfirmed) {
                        Subtitle(
                            text = stringResource(R.string.need_to_confirm),
                            color = MaterialTheme.colorScheme.error,
                        )
                    }
                }
            }
        },
    )
}

@Composable
fun BiometricProfileSetting(
    isBiometricAllowed: Boolean,
    modifier: Modifier = Modifier,
) {
    var tempIsBiometricAllowed by remember { mutableStateOf(isBiometricAllowed) }
    ProfileSetting(
        onClick = { },
        modifier = modifier,
        title = stringResource(R.string.login_by_biometrics),
        contentEnd = {
            DiscountClubSwitch(
                isChecked = tempIsBiometricAllowed,
                onCheckedChange = { tempIsBiometricAllowed = it },
            )
        },
    )
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
            isEmailConfirmed = false,
        ),
        settings = Settings(
            isBiometricAuthAllowed = true,
            language = Language.RUSSIAN,
        ),
        onMyPurchasesButtonClick = {},
        onRegistrationButtonClick = {},
    )
}

@Composable
fun Language.asString(): String = when (this) {
    Language.RUSSIAN -> stringResource(R.string.russian)
}
