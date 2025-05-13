package com.example.fetch.groupList.data.repository

import com.example.fetch.core.domain.DataError
import com.example.fetch.core.domain.Result
import com.example.fetch.core.domain.map
import com.example.fetch.groupList.data.network.RemoteListSource
import com.example.fetch.groupList.data.toGroupListItem
import com.example.fetch.groupList.domain.DataRepository
import com.example.fetch.groupList.domain.GroupListItem

class DefaultRepository(
    private val remoteDataSource : RemoteListSource
) : DataRepository{ // how we define data functions
    override suspend fun searchApi(): Result<List<GroupListItem>, DataError.Remote> {
        return remoteDataSource
            .searchApi()
            .map { thing ->
                thing.map {
                    it.toGroupListItem()
                }
            }
    }
}