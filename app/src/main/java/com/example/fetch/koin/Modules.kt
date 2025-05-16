package com.example.fetch.koin

import com.example.fetch.core.data.HttpClientFactory
import com.example.fetch.groupList.data.Manipulation.ManipulationMethods
import com.example.fetch.groupList.data.network.RemoteApi
import com.example.fetch.groupList.data.network.RemoteListSource
import com.example.fetch.groupList.data.repository.DefaultRepository
import com.example.fetch.groupList.domain.DataManipulations
import com.example.fetch.groupList.domain.DataRepository
import com.example.fetch.groupList.presentation.SelectedGroupListViewModel
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module


val modules = module {
    single{ HttpClientFactory.create(engine = OkHttp.create())}
    singleOf(::RemoteApi).bind<RemoteListSource>()
    singleOf(::DefaultRepository).bind<DataRepository>()
    singleOf(::ManipulationMethods).bind<DataManipulations>()
    viewModelOf(::SelectedGroupListViewModel)
}