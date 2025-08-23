package com.example.discountclub.domain.repository

import com.example.discountclub.domain.model.Purchase

interface PurchasesRepositoryApi {
    suspend fun getPurchases(): List<Purchase>
}