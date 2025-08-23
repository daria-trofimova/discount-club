package com.example.discountclub.domain.dto

data class LoginParameters(
    val participantNumber: String,
    val code: String,
    val name: String,
    val lastName: String,
)