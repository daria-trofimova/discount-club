package com.example.discountclub.ui.components

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.discountclub.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscountClubTopBar(
    @StringRes titleResId: Int?,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            if (titleResId != null) {
                Text(text = stringResource(titleResId))
            }
        },
        navigationIcon = {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.navigate_back)
                )
            }
        }, modifier = modifier
    )
}

@Preview
@Composable
fun DiscountClubTopBarPreview() {
    DiscountClubTopBar(
        titleResId = android.R.string.untitled,
        navigateUp = {},
    )
}
