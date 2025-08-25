package com.example.discountclub.ui.screens.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.discountclub.domain.RegisterUseCase
import com.example.discountclub.domain.dto.RegistrationParameters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
) : ViewModel() {
    private val _uiState =
        MutableStateFlow<RegistrationUiState>(RegistrationUiState.Editing(RegistrationForm.EMPTY))
    val uiState: StateFlow<RegistrationUiState> = _uiState.asStateFlow()

    private val _effects = Channel<RegistrationEffect>()
    val effect = _effects.receiveAsFlow()

    // TODO: debounce validation
    fun sendIntent(intent: RegistrationIntent) {
        when (intent) {
            is RegistrationIntent.UpdateParticipantNumber -> {
                updateUiState { it.updateParticipantNumber(intent.text) }
            }

            is RegistrationIntent.UpdateCode -> {
                updateUiState { it.updateCode(intent.text) }
            }

            is RegistrationIntent.UpdateName -> {
                updateUiState { it.updateName(intent.text) }
            }

            is RegistrationIntent.UpdateLastName -> {
                updateUiState { it.updateLastName(intent.text) }
            }

            is RegistrationIntent.Submit -> submitForm()
        }
    }

    private fun updateUiState(update: (RegistrationForm) -> RegistrationForm) {
        _uiState.value = RegistrationUiState.Editing(update(_uiState.value.form))
    }

    private fun submitForm() {
        val form = _uiState.value.form
        if (form.isValid) {
            _uiState.value = RegistrationUiState.Loading(form)
            viewModelScope.launch {
                registerUseCase(parameters = form.toRegistrationParameters())
                _effects.send(RegistrationEffect.NavigateBack)
            }
        }
    }
}

sealed class RegistrationUiState {
    abstract val form: RegistrationForm

    data class Loading(override val form: RegistrationForm) : RegistrationUiState()
    data class Editing(override val form: RegistrationForm) : RegistrationUiState()
}

sealed class RegistrationIntent {
    class UpdateParticipantNumber(val text: String) : RegistrationIntent()
    class UpdateCode(val text: String) : RegistrationIntent()
    class UpdateName(val text: String) : RegistrationIntent()
    class UpdateLastName(val text: String) : RegistrationIntent()
    data object Submit : RegistrationIntent()
}

sealed class RegistrationEffect {
    data object NavigateBack : RegistrationEffect()
}

data class RegistrationForm(
    val participantNumber: InputText,
    val code: InputText,
    val name: InputText,
    val lastName: InputText,
) {
    val isValid: Boolean
        get() = participantNumber.isValid &&
                code.isValid &&
                name.isValid &&
                lastName.isValid

    fun updateParticipantNumber(text: String): RegistrationForm {
        val error = ParticipantNumberValidator.validate(text)
        return copy(participantNumber = InputText(text = text, error = error))
    }

    fun updateCode(text: String): RegistrationForm {
        return copy(code = InputText(text = text, error = NotBlankValidator.validate(text)))
    }

    fun updateName(text: String): RegistrationForm {
        return copy(name = InputText(text = text, error = NotBlankValidator.validate(text)))
    }

    fun updateLastName(text: String): RegistrationForm {
        return copy(lastName = InputText(text = text, error = NotBlankValidator.validate(text)))
    }

    companion object {
        val EMPTY = RegistrationForm(
            participantNumber = InputText.EMPTY,
            code = InputText.EMPTY,
            name = InputText.EMPTY,
            lastName = InputText.EMPTY,
        )
    }
}

fun RegistrationForm.toRegistrationParameters(): RegistrationParameters {
    return RegistrationParameters(
        participantNumber = participantNumber.text,
        code = code.text,
        name = name.text,
        lastName = lastName.text,
    )
}

enum class ValidationError {
    EmptyOrBlankError,
    InvalidFormatError,
}

interface Validator {
    fun validate(value: String): ValidationError?
}

object NotBlankValidator : Validator {
    override fun validate(value: String): ValidationError? {
        return if (value.isBlank()) ValidationError.EmptyOrBlankError else null
    }
}

object ParticipantNumberValidator : Validator {

    private const val LENGTH = 16

    override fun validate(value: String): ValidationError? {
        return when {
            value.isBlank() -> ValidationError.EmptyOrBlankError
            value.length != LENGTH || !value.all { it.isDigit() } -> ValidationError.InvalidFormatError
            else -> null
        }
    }
}

class InputText(
    val text: String,
    val error: ValidationError? = null,
) {
    val isValid: Boolean get() = error == null

    companion object {
        val EMPTY = InputText(
            text = "",
            error = ValidationError.EmptyOrBlankError,
        )
    }
}