package com.example.discountclub.ui.extensions

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle

fun AnnotatedString.Builder.appendAnnotation(
    text: String,
    tag: String,
    annotation: String = tag,
    style: SpanStyle? = null
) {
    val start = length
    append(text)
    val end = length
    style?.let {
        addStyle(
            style = it,
            start = start,
            end = end
        )
    }
    addStringAnnotation(
        tag = tag,
        annotation = annotation,
        start = start,
        end = end
    )
}