package com.example.discountclub.domain

import com.example.discountclub.data.PurchasesRepository
import com.example.discountclub.domain.model.Purchase
import java.util.Date
import javax.inject.Inject

class GetPurchasesByDateUseCase @Inject constructor(
    private val purchasesRepository: PurchasesRepository,
) {
    // TODO: Don't forget to group by date! Not by timestamp
    suspend operator fun invoke(): Map<Date, List<Purchase>> {
        TODO()
    }
}