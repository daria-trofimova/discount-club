package com.example.discountclub.ui.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.discountclub.R
import com.example.discountclub.domain.model.Language

@Composable
fun Language.asString(): String = when (this) {
    Language.RUSSIAN -> stringResource(R.string.russian)
}
