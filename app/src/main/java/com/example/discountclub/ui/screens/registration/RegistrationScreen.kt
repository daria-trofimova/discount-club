package com.example.discountclub.ui.screens.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.discountclub.R
import com.example.discountclub.ui.LoadingOverlay

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
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        RegistrationFormFields(
            form = state.form,
            onParticipantNumberChange = onParticipantNumberChange,
            onCodeChange = onCodeChange,
            onNameChange = onNameChange,
            onLastNameChange = onLastNameChange,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = stringResource(R.string.by_clicking_the_continue_button),
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onSubmitButtonClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            enabled = state.form.isValid
        ) {
            Text(
                text = stringResource(R.string.continue_),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun RegistrationFormFields(
    form: RegistrationForm,
    onParticipantNumberChange: (String) -> Unit,
    onCodeChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        CustomTextField(
            value = form.participantNumber.text,
            onValueChange = onParticipantNumberChange,
            label = stringResource(R.string.participant_number),
            placeholder = stringResource(R.string.the_16_digit_number_you_received),
            error = if (form.participantNumber.error == ValidationError.InvalidFormatError) {
                stringResource(R.string.the_number_must_contain_16_digits)
            } else null,
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next,
        )

        Spacer(modifier = Modifier.height(24.dp))

        CustomTextField(
            value = form.code.text,
            onValueChange = onCodeChange,
            label = stringResource(R.string.code),
            placeholder = stringResource(R.string.the_code_you_received),
            modifier = Modifier.fillMaxWidth(),
            imeAction = ImeAction.Next,
        )

        Spacer(modifier = Modifier.height(24.dp))

        CustomTextField(
            value = form.name.text,
            onValueChange = onNameChange,
            label = stringResource(R.string.name),
            placeholder = stringResource(R.string.name_in_latin_as_in_passport),
            modifier = Modifier.fillMaxWidth(),
            imeAction = ImeAction.Next,
        )

        Spacer(modifier = Modifier.height(24.dp))

        CustomTextField(
            value = form.lastName.text,
            onValueChange = onLastNameChange,
            label = stringResource(R.string.last_name),
            placeholder = stringResource(R.string.last_name_in_latin_as_in_passport),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    error: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(text = label) },
            modifier = Modifier.fillMaxWidth(),
            isError = error != null,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
        )
        if (error != null) {
            Text(
                text = error,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        } else {
            Text(
                text = placeholder,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
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