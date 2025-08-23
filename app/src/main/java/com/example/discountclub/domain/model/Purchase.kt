package com.example.discountclub.domain.model

import java.util.Date

data class Purchase(val date: Date, val items: List<PurchaseItem>)