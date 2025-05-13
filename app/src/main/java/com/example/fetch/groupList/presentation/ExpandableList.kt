package com.example.fetch.groupList.presentation

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    LazyColumn (modifier = Modifier){
        items(groups){ id ->
            ListItems (
                headlineText = "Group ID: $id",
                expandedContent = {
                    LazyColumn(
                        horizontalAlignment = CenterHorizontally,
                        modifier = Modifier
                            .width(400.dp)
                            .padding(start = 4.dp, end = 4.dp, top = 4.dp)
                            .height(305.dp) //extra 5dp to hint that this can scroll
                    ) {
                        items(state.filteredGroupList.filter { it.listId == id }) { listItem ->
                            ListItem(
                                headlineContent = { Text("Name:  ${listItem.name}") }
                            )
                            HorizontalDivider(thickness = Hairline)
                        }
                    }
                },
                onToggle = { onAction(GroupListAction.onGroupListClick(id)) },
                isExpanded = state.selectedGroupIndex[id] ?: false,
                supportingText = "Number of Items: ${state.filteredGroupList.filter { it.listId == id }.size}"
            )
            HorizontalDivider(thickness = 1.dp)
        }
        item {
            Spacer(modifier = Modifier.size(32.dp))
        }
    }
}

//@Preview
//@Composable
//fun ExpandableListPreview() {
//    //ExpandableListScreen()
//}