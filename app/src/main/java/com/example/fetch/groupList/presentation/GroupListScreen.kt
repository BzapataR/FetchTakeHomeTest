package com.example.fetch.groupList.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fetch.groupList.domain.GroupListItem
import com.example.fetch.ui.theme.FetchTheme
import org.koin.compose.viewmodel.koinViewModel


@Composable // The UI components added independently for preview
fun GroupListScreen(
    state: GroupListState,
    onAction: (GroupListAction) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding(),
        //horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Spacer(modifier = Modifier.size(12.dp))

        Text(
            text = "Groups",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 4.dp)
        )
        if(state.isLoading) {
            // while empty this space is so that List is empty error wont show while loading to avoid confusion
        }
        else {
            when {
                    state.errorMessage != null -> {
                        Box(
                            Modifier.fillMaxSize().weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = state.errorMessage.toString(),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.headlineLarge,
                                color = MaterialTheme.colorScheme.error,
                            )
                        }
                    }

                        state.groupList.isEmpty () -> {
                            Box(
                                Modifier.fillMaxSize().weight(1f),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "No groups found",
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = Color.Gray,
                                )
                            }
                }

                else -> {
                    ExpandableList(state = state, onAction = onAction )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupListRoot(
    viewModel: SelectedGroupListViewModel = koinViewModel(),
    ) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    PullToRefreshBox(
        modifier = Modifier.fillMaxSize(),
        onRefresh = { viewModel.searchGroupList() },
        isRefreshing = state.isLoading
    ) {
        GroupListScreen(
            state = state,
            onAction =  { action ->
                when (action) { //expandable for more actions
                    is GroupListAction.onGroupListClick -> viewModel.onAction(action)
                    else -> Unit
                }
            }
        )
    }
}
@Preview
@Composable
fun GroupListScreenPreview() {

    val dummyEntry = GroupListItem (
        id =1,
        listId=1,
        name = "target"
    )
    val dummyEntry2 = GroupListItem (
        id =1,
        listId=3,
        name = "target"
    )
    val dummyEntry3 = GroupListItem (
        id =1,
        listId=2,
        name = "target"
    )
    val dummyEntry4 = GroupListItem (
        id =1,
        listId=2,
        name = "target"
    )
    val dummyGroupList = listOf(
        dummyEntry,
        dummyEntry2,
        dummyEntry3,
        dummyEntry4
    )
    FetchTheme {
        Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
            GroupListScreen(
                state = GroupListState(
                    isLoading = false,
                    groupList = dummyGroupList,
                    groups = dummyGroupList.map { it.listId }.toSet().toList().sorted(),
                    filteredGroupList = dummyGroupList.filter { !it.name.isNullOrBlank() }
                        .sortedBy { it.id },
                    errorMessage = null
                ),
            )
        }
    }
}