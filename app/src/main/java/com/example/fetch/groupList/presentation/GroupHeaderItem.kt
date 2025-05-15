package com.example.fetch.groupList.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate

@Composable
fun GroupHeaderItem (
    headlineText: String,
    supportingText: String,
    isExpanded : Boolean
) {
    val rotationAngle by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        label = "Arrow Rotation"
    )
    ListItem(
        modifier = Modifier,
        headlineContent = { Text(headlineText)},
        supportingContent = { Text(supportingText) },
        trailingContent = {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = headlineText,
                modifier = Modifier.rotate(rotationAngle)
            )
        },
    )
}
