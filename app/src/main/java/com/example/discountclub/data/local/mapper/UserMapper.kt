package com.example.discountclub.data.local.mapper

import com.example.discountclub.data.local.model.User
import com.example.discountclub.domain.model.User as DomainUser

fun User.toDomainUser(): DomainUser =
    DomainUser(
        phoneNumber = phoneNumber,
        name = name,
        lastName = lastName,
        email = email,
    )