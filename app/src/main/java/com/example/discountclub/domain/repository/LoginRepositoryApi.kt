package com.example.discountclub.domain.repository

import com.example.discountclub.domain.dto.LoginParameters

interface LoginRepositoryApi {

    suspend fun login(parameters: LoginParameters): Result<Unit>
}