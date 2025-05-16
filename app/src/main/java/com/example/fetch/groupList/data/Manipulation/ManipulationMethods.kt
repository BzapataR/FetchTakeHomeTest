package com.example.fetch.groupList.data.Manipulation

import com.example.fetch.groupList.domain.DataManipulations
import com.example.fetch.groupList.domain.GroupListItem

class ManipulationMethods: DataManipulations {
    override fun filterGroupList(results: List<GroupListItem>): List<GroupListItem> {
       return results.filter { !it.name.isNullOrBlank()}.sortedBy { it.id }
    }
    override fun getGroupCount(results: List<GroupListItem>): List<Int> {
        return results.map {it.listId}.toSet().toList().sorted()
    }
    override fun uniqueGroupIds(results: List<GroupListItem>): List<Int> {
        return results.map {it.listId }.toSet().toList().sorted()
    }

}