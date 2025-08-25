package com.example.discountclub.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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
import com.example.discountclub.ui.extensions.asString

@Composable
fun ProfileScreen(
    onNavigateToRegistration: () -> Unit,
    onNavigateToMyPurchases: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        is ProfileUiState.Loading -> LoadingProfile(modifier)
        is ProfileUiState.UnAuthenticated -> {
            /* TODO: Handle unauthenticated state  */
        }

        is ProfileUiState.Authenticated -> ProfileContent(
            state = uiState as ProfileUiState.Authenticated,
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
private fun ProfileContent(
    state: ProfileUiState.Authenticated,
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
        Spacer(Modifier.height(32.dp))
        ProfileInfo(
            user = state.user,
        )
        Spacer(Modifier.height(40.dp))
        Text(
            text = stringResource(R.string.my_purchases),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(Modifier.height(12.dp))
        MyPurchasesProfileSetting(onMyPurchasesButtonClick)
        Spacer(Modifier.height(32.dp))
        ProfileSettings(
            user = state.user,
            settings = state.settings,
            onRegistrationButtonClick = onRegistrationButtonClick,
        )
    }
}

@Composable
private fun ProfileInfo(
    user: User,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        UserFullName(user)
        Spacer(Modifier.height(12.dp))
        Text(
            text = user.phoneNumber,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun UserFullName(
    user: User,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = user.name ?: stringResource(R.string.name),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = user.lastName ?: stringResource(R.string.last_name),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(Modifier.width(12.dp))
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = stringResource(R.string.edit_first_and_last_name),
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .size(24.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }) {
                        /* TODO: Implement navigation to edit profile */
                    }
            )
        }
    }
}

@Composable
private fun ProfileSettings(
    user: User,
    settings: Settings,
    onRegistrationButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.settings),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(Modifier.height(12.dp))
        EmailProfileSetting(
            email = user.email,
            isEmailConfirmed = user.isEmailConfirmed,
        )
        Spacer(Modifier.height(12.dp))
        BiometricProfileSetting(
            isBiometricAllowed = settings.isBiometricAuthAllowed,
        )
        Spacer(Modifier.height(12.dp))
        ProfileSetting(
            onClick = { /* TODO: Implement change 4-digit code */ },
            title = stringResource(R.string.change_4_digit_code)
        )
        Spacer(Modifier.height(12.dp))
        ProfileSetting(
            onClick = onRegistrationButtonClick,
            title = stringResource(R.string.registration_for_bank_clients)
        )
        Spacer(Modifier.height(12.dp))
        ProfileSetting(
            onClick = { /* TODO: Implement language selection */ },
            subtitle = settings.language.asString(),
            title = stringResource(R.string.language)
        )
    }
}

@Composable
private fun MyPurchasesProfileSetting(
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
private fun EmailProfileSetting(
    email: String?,
    isEmailConfirmed: Boolean,
    modifier: Modifier = Modifier,
) {
    ProfileSetting(
        onClick = { /* TODO: Implement email editing */ },
        modifier = modifier,
        title = stringResource(R.string.email),
        contentStart = {
            email?.let {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Subtitle(text = it)
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
private fun BiometricProfileSetting(
    isBiometricAllowed: Boolean,
    modifier: Modifier = Modifier,
) {
    ProfileSetting(
        onClick = { },
        modifier = modifier,
        title = stringResource(R.string.login_by_biometrics),
        contentEnd = {
            var tempIsBiometricAllowed by remember { mutableStateOf(isBiometricAllowed) }
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
    ProfileContent(
        state = ProfileUiState.Authenticated(
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
            )
        ),
        onMyPurchasesButtonClick = {},
        onRegistrationButtonClick = {},
    )
}