package com.example.discountclub.data.remote.mapper

import com.example.discountclub.data.remote.model.PurchaseResponse
import com.example.discountclub.domain.model.Purchase
import com.example.discountclub.domain.model.PurchaseItem

fun PurchaseResponse.toDomainPurchase(): Purchase {
    return Purchase(date = date, items = name.map { PurchaseItem(name = it) })
}