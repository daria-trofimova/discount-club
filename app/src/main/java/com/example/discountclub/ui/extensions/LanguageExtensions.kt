package com.example.discountclub.ui.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.discountclub.R
import com.example.discountclub.domain.model.Settings

@Composable
fun Settings.Language.asString(): String = when (this) {
    Settings.Language.RUSSIAN -> stringResource(R.string.russian)
}
