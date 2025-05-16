package com.example.fetch.groupList.domain

interface DataManipulations {
    fun uniqueGroupIds(groupList: List<GroupListItem>): List<Int>
    fun filterGroupList(groupList: List<GroupListItem>): List<GroupListItem>
    fun getGroupCount(groupList: List<GroupListItem>): List<Int>


}