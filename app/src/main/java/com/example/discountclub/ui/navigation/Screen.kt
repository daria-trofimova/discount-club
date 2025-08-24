package com.example.discountclub.ui.navigation

import androidx.annotation.StringRes
import com.example.discountclub.R

enum class Screen(
    @StringRes val titleResId: Int,
) {
    Profile(titleResId = R.string.profile),
    Registration(titleResId = R.string.registration_for_bank_clients),
    MyPurchases(titleResId = R.string.my_purchases),
}