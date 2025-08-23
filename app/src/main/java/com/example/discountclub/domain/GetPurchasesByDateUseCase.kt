package com.example.discountclub.domain

import com.example.discountclub.core.common.toMidnight
import com.example.discountclub.data.PurchasesRepository
import com.example.discountclub.domain.model.DatePurchases
import javax.inject.Inject

class GetPurchasesByDateUseCase @Inject constructor(
    private val repository: PurchasesRepository,
) {

    suspend operator fun invoke(): List<DatePurchases> {
        return repository.getPurchases()
            .groupBy { purchase ->
                purchase.date.toMidnight()
            }
            .map { (date, purchases) ->
                val purchaseItems = purchases.flatMap { it.items }
                DatePurchases(date = date, items = purchaseItems)
            }.sortedByDescending { it.date }

    }
}