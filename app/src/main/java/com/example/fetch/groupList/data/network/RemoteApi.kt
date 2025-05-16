package com.example.fetch.groupList.data.network

import com.example.fetch.core.data.safeCall
import com.example.fetch.core.domain.DataError
import com.example.fetch.core.domain.Result
import com.example.fetch.groupList.data.Dto.ResultsDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private  const val URL = "https://hiring.fetch.com/hiring.json"
class RemoteApi (
    private val httpClient: HttpClient
) : RemoteListSource {
    override suspend fun searchApi(): Result<List<ResultsDto>, DataError.Remote> {
        return safeCall {
            httpClient.get(
                urlString = URL
            ) {
                parameter("fields", "id, listId, name")
            }.body()
        }
    }
}