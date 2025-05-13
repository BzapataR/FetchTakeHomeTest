package com.example.fetch.groupList.data.network

import com.example.fetch.core.domain.DataError
import com.example.fetch.core.domain.Result
import com.example.fetch.groupList.data.ResultsDto

interface RemoteListSource {
    suspend fun searchApi(): Result<List<ResultsDto>, DataError.Remote>
}