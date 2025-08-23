package com.example.discountclub.domain.model

import java.util.Date

data class DatePurchases(val date: Date, val items: List<PurchaseItem>)