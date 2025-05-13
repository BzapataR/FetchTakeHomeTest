package com.example.fetch.groupList.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate

@Composable
fun ListItems (
    modifier: Modifier = Modifier,
    headlineText: String,
    supportingText: String,
    onToggle: () -> Unit,
    expandedContent: @Composable () -> Unit,
    isExpanded : Boolean
) {
    val rotationAngle by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        label = "Arrow Rotation"
    )

    Column (
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize()
            .clickable{ onToggle() },
        horizontalAlignment = CenterHorizontally
    ) {
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
        AnimatedVisibility(visible = isExpanded) {
            expandedContent()
        }
    }
}
