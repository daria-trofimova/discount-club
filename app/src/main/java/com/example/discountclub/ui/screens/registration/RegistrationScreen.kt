package com.example.discountclub.ui.screens.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.discountclub.R
import com.example.discountclub.ui.components.DiscountClubButton
import com.example.discountclub.ui.components.DiscountClubTextField
import com.example.discountclub.ui.components.LoadingOverlay

@Composable
fun RegistrationScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is RegistrationEffect.NavigateBack -> onNavigateBack()
            }
        }
    }
    val currentUiState = uiState.value
    RegistrationScreenContent(
        state = currentUiState,
        onParticipantNumberChange = {
            viewModel.sendIntent(RegistrationIntent.UpdateParticipantNumber(it))
        },
        onCodeChange = { viewModel.sendIntent(RegistrationIntent.UpdateCode(it)) },
        onNameChange = { viewModel.sendIntent(RegistrationIntent.UpdateName(it)) },
        onLastNameChange = { viewModel.sendIntent(RegistrationIntent.UpdateLastName(it)) },
        onSubmitButtonClick = { viewModel.sendIntent(RegistrationIntent.Submit) },
        modifier = modifier,
    )
    if (currentUiState is RegistrationUiState.Loading) {
        LoadingOverlay(modifier = modifier)
    }
}

@Composable
private fun RegistrationScreenContent(
    state: RegistrationUiState,
    onParticipantNumberChange: (String) -> Unit,
    onCodeChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onSubmitButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RegistrationFormFields(
            form = state.form,
            onParticipantNumberChange = onParticipantNumberChange,
            onCodeChange = onCodeChange,
            onNameChange = onNameChange,
            onLastNameChange = onLastNameChange,
        )

        Spacer(modifier = Modifier.height(32.dp))
        RegistrationTerms()
        Spacer(modifier = Modifier.height(16.dp))
        DiscountClubButton(
            text = stringResource(R.string.continue_),
            onClick = onSubmitButtonClick,
            isEnabled = state.form.isValid
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun RegistrationTerms(
    modifier: Modifier = Modifier,
) {
    val start = stringResource(R.string.by_clicking_the_continue_button)
    val end = stringResource(R.string.terms_of_participation)
    val annotatedString = buildAnnotatedString {
        append(start)
        append(" ")
        val linkStart = length
        append(end)
        val linkEnd = length
        addStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline
            ),
            start = linkStart,
            end = linkEnd
        )
        addStringAnnotation(
            tag = end,
            annotation = end,
            start = linkStart,
            end = linkEnd
        )
    }
    Text(
        text = annotatedString,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        textAlign = TextAlign.Center,
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
private fun RegistrationFormFields(
    form: RegistrationForm,
    onParticipantNumberChange: (String) -> Unit,
    onCodeChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        DiscountClubTextField(
            value = form.participantNumber.text,
            onValueChange = onParticipantNumberChange,
            label = stringResource(R.string.participant_number),
            hint = stringResource(R.string.the_16_digit_number_you_received),
            error = if (form.participantNumber.error == ValidationError.InvalidFormatError) {
                stringResource(R.string.the_number_must_contain_16_digits)
            } else null,
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next,
        )
        Spacer(modifier = Modifier.height(20.dp))
        DiscountClubTextField(
            value = form.code.text,
            onValueChange = onCodeChange,
            label = stringResource(R.string.code),
            hint = stringResource(R.string.the_code_you_received),
            modifier = Modifier.fillMaxWidth(),
            imeAction = ImeAction.Next,
        )
        Spacer(modifier = Modifier.height(20.dp))
        DiscountClubTextField(
            value = form.name.text,
            onValueChange = onNameChange,
            label = stringResource(R.string.name),
            hint = stringResource(R.string.name_in_latin_as_in_passport),
            modifier = Modifier.fillMaxWidth(),
            imeAction = ImeAction.Next,
        )
        Spacer(modifier = Modifier.height(20.dp))
        DiscountClubTextField(
            value = form.lastName.text,
            onValueChange = onLastNameChange,
            label = stringResource(R.string.last_name),
            hint = stringResource(R.string.last_name_in_latin_as_in_passport),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun RegistrationPreview() {
    RegistrationScreenContent(
        state = RegistrationUiState.Editing(
            RegistrationForm(
                participantNumber = InputText.EMPTY,
                code = InputText.EMPTY,
                name = InputText.EMPTY,
                lastName = InputText.EMPTY,
            )
        ),
        onParticipantNumberChange = {},
        onCodeChange = {},
        onNameChange = {},
        onLastNameChange = {},
        onSubmitButtonClick = {}
    )
}