package com.example.fetch.groupList.data.Dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultsDto(
    @SerialName("id") val id : Int,
    @SerialName("listId") val listId : Int,
    @SerialName("name") val name : String? = null
)