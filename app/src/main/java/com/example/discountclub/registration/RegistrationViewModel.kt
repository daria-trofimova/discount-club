package com.example.discountclub.registration

import androidx.lifecycle.ViewModel
import com.example.discountclub.domain.LoginUseCase
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel()