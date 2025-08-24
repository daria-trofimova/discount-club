package com.example.discountclub.ui.screens.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileSetting(
    title: String,
    subtitle: String,
    contentEnd: @Composable () -> Unit = { ArrowRightIcon() },
    onClick: () -> Unit,
) {
    ProfileSetting(
        title = title, contentBottom = {
            Text(
                text = subtitle,
                fontSize = 14.sp
            )
        }, contentEnd = contentEnd,
        onClick = onClick
    )
}

@Composable
fun ProfileSetting(
    title: String,
    contentBottom: @Composable () -> Unit = {},
    contentEnd: @Composable () -> Unit = { ArrowRightIcon() },
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 16.sp
                )
                contentBottom()
            }
            contentEnd()
        }
    }
}

@Composable
fun ArrowRightIcon() {
    Icon(
        imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
        contentDescription = null,
        modifier = Modifier.size(20.dp)
    )
}

@Preview
@Composable
private fun ProfileSettingPreview() {
    ProfileSetting(
        title = "Title",
        subtitle = "Subtitle",
        onClick = {}
    )
}
