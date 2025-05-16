package com.example.fetch.groupList.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

