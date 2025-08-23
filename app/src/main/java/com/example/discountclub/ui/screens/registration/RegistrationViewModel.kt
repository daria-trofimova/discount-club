package com.example.discountclub.ui.screens.registration

import androidx.lifecycle.ViewModel
import com.example.discountclub.domain.LoginUseCase
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel()