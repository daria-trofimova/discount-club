package com.example.discountclub.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscountClubTopBar(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(title = {}, navigationIcon = {
        IconButton(navigateUp) { }
    }, modifier = modifier)
}