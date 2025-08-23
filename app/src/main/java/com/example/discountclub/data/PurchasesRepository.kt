package com.example.discountclub.data

import com.example.discountclub.data.remote.PurchasesRemoteDataSource
import com.example.discountclub.data.remote.mapper.toDomainPurchase
import com.example.discountclub.domain.model.Purchase
import com.example.discountclub.domain.repository.PurchasesRepositoryApi
import javax.inject.Inject

class PurchasesRepository @Inject constructor(
    private val remoteDataSource: PurchasesRemoteDataSource,
) : PurchasesRepositoryApi {

    override suspend fun getPurchases(): List<Purchase> {
        return remoteDataSource.getPurchases().map { it.toDomainPurchase() }
    }
}