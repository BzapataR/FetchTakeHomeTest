package com.example.fetch.groupList.presentation

import com.example.fetch.groupList.domain.GroupListItem

data class GroupListState( // truth be told the groups and the grouplist could've been added to a Map
    val isLoading : Boolean = true,
    val groupList : List<GroupListItem> = emptyList(),
    val groups : List<Int> = emptyList(),
    val filteredGroupList : List<GroupListItem> = emptyList(),
    val selectedGroupIndex: Map<Int, Boolean> = emptyMap(),
    val errorMessage: String? = null
)
