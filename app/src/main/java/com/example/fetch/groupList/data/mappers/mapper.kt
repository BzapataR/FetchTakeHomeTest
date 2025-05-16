package com.example.fetch.groupList.data.mappers

import com.example.fetch.groupList.data.Dto.ResultsDto
import com.example.fetch.groupList.domain.GroupListItem

fun ResultsDto.toGroupListItem(): GroupListItem {
    return GroupListItem(
        id = id,
        listId = listId,
        name = name
    )
}