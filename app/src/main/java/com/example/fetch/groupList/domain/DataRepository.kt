package com.example.fetch.groupList.domain

import com.example.fetch.core.domain.DataError
import com.example.fetch.core.domain.Result

interface DataRepository { // This can be expanded to include another source or local source
    suspend fun searchApi() : Result<List<GroupListItem>, DataError.Remote>
}