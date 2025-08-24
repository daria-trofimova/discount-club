package com.example.discountclub.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.discountclub.ui.navigation.Screen
import com.example.discountclub.ui.screens.profile.ProfileScreen
import com.example.discountclub.ui.screens.purchases.MyPurchasesScreen
import com.example.discountclub.ui.screens.registration.RegistrationScreen

@Composable
fun DiscountClubApp() {
    val navController = rememberNavController()
    Scaffold(topBar = {
        DiscountClubTopBar(navigateUp = { navController.navigateUp() })
    }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Profile.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            profileScreen(
                onNavigateToRegistration = { navController.navigate(Screen.Registration.name) },
                onNavigateToMyPurchases = { navController.navigate(Screen.MyPurchases.name) },
            )
            registrationScreen(onNavigateBack = { navController.navigateUp() })
            myPurchasesScreen()
        }
    }
}

private fun NavGraphBuilder.profileScreen(
    onNavigateToRegistration: () -> Unit,
    onNavigateToMyPurchases: () -> Unit,
) {
    composable(route = Screen.Profile.name) {
        ProfileScreen(
            onNavigateToRegistration = onNavigateToRegistration,
            onNavigateToMyPurchases = onNavigateToMyPurchases,
        )
    }
}

private fun NavGraphBuilder.registrationScreen(
    onNavigateBack: () -> Unit,
) {
    composable(route = Screen.Registration.name) {
        RegistrationScreen(onNavigateBack = onNavigateBack)
    }
}

private fun NavGraphBuilder.myPurchasesScreen() {
    composable(route = Screen.MyPurchases.name) {
        MyPurchasesScreen()
    }
}