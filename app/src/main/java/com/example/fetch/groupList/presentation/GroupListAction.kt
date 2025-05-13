package com.example.fetch.groupList.presentation

sealed interface GroupListAction {
    data class onGroupListClick(val index: Int): GroupListAction
}