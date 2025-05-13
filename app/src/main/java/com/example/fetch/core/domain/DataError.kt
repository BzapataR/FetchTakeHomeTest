package com.example.fetch.core.domain

sealed interface DataError: Error {
    enum class Remote: DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        SERVER,
        SERIALIZATION,
        UNKNOWN_ERROR
    }

    enum class Local: DataError { // for local data sources
        DISK_FULL,
        UNKNOWN
    }
}