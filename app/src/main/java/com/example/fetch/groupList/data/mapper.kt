package com.example.fetch.groupList.data

import com.example.fetch.groupList.domain.GroupListItem

fun ResultsDto.toGroupListItem(): GroupListItem {
    return GroupListItem(
        id = id,
        listId = listId,
        name = name
    )
}