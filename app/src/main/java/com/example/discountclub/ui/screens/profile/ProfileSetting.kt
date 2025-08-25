package com.example.discountclub.ui.screens.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProfileSetting(
    onClick: () -> Unit,
    subtitle: String,
    modifier: Modifier = Modifier,
    title: String? = null,
    contentEnd: @Composable ProfileSettingScope.() -> Unit = { ArrowRightIcon() },
) {
    ProfileSetting(
        onClick = onClick,
        modifier = modifier,
        title = title,
        contentStart = {
            Subtitle(text = subtitle)
        },
        contentEnd = contentEnd,
    )
}

@Composable
fun ProfileSetting(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    contentStart: @Composable ProfileSettingScope.() -> Unit = {},
    contentEnd: @Composable ProfileSettingScope.() -> Unit = { ArrowRightIcon() },
) {
    val scope = remember { ProfileSettingScope() }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                if (title != null) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    if (contentStart != {}) {
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
                scope.contentStart()
            }
            Spacer(modifier = Modifier.width(12.dp))
            scope.contentEnd()
        }
    }
}

class ProfileSettingScope {
    @Composable
    fun ArrowRightIcon(
        modifier: Modifier = Modifier,
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = modifier.size(24.dp)
        )
    }

    @Composable
    fun Subtitle(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = color,
            modifier = modifier
        )
    }
}

@Preview
@Composable
private fun ProfileSettingPreview() {
    ProfileSetting(
        title = "Title",
        subtitle = "Subtitle",
        onClick = {},
    )
}
