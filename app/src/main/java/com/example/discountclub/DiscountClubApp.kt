package com.example.discountclub

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.discountclub.navigation.Screen
import com.example.discountclub.profile.ProfileScreen
import com.example.discountclub.purchases.MyPurchasesScreen
import com.example.discountclub.registration.RegistrationScreen

@Composable
fun DiscountClubApp() {
    val navController = rememberNavController()
    Scaffold(topBar = {}) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Profile.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Screen.Profile.name) { ProfileScreen() }
            composable(route = Screen.Registration.name) { RegistrationScreen() }
            composable(route = Screen.MyPurchases.name) { MyPurchasesScreen() }
        }
    }
}