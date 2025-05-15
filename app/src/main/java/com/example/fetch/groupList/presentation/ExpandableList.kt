package com.example.fetch.groupList.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp.Companion.Hairline
import androidx.compose.ui.unit.dp


@Composable
fun ExpandableList (
    state: GroupListState,
    onAction: (GroupListAction) -> Unit = {},
) {
    val groups = state.groups
    LazyColumn (modifier = Modifier.fillMaxSize()){
        groups.forEach { groupID ->
            val isExpanded = (state.selectedGroupIndex[groupID] ?: false)
            val itemsInGroup = state.filteredGroupList.filter { it.listId == groupID }
            item(key = groupID) {
                Column(modifier = Modifier.clickable {onAction(GroupListAction.onGroupListClick(groupID))}) {
                    GroupHeaderItem(
                        headlineText = "Group ID: $groupID",
                        supportingText = "Number of Items: ${itemsInGroup.size}",
                        isExpanded = isExpanded
                    )
                        AnimatedVisibility(visible = isExpanded) {
                            Column(
                                modifier = Modifier.padding(
                                    start = 4.dp,
                                    end = 4.dp,
                                    top = 4.dp
                                )
                            ) {
                                itemsInGroup.forEach { item ->
                                    ListItem(
                                        headlineContent = { Text("Name:  ${item.name}") },
                                    )
                                    HorizontalDivider(thickness = Hairline)
                                }
                            }
                        }

                    HorizontalDivider(thickness = 1.dp)
                }
            }
        }
    }
}

