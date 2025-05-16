package com.example.fetch.groupList.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetch.core.domain.onError
import com.example.fetch.core.domain.onSuccess
import com.example.fetch.groupList.domain.DataManipulations
import com.example.fetch.groupList.domain.DataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SelectedGroupListViewModel(
    private val repository: DataRepository,
    private val manipulations: DataManipulations
) : ViewModel() {

    private val _state = MutableStateFlow(GroupListState())
    val state = _state
        .onStart {
            searchGroupList()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _state.value
        )

    fun searchGroupList() = viewModelScope.launch {
        _state.update {
            it.copy(
                isLoading = true,
            )
        }
        repository.searchApi()
            .onSuccess { results ->
                _state.update {
                    val uniqueGroupIds = manipulations.uniqueGroupIds(results)//results.map { it.listId }.toSet().toList().sorted()
                    val initialSelectedGroupIndex = uniqueGroupIds.associateWith { false }
                    it.copy(
                        isLoading = false,
                        errorMessage = null,
                        groupList = results,
                        filteredGroupList = manipulations.filterGroupList(results),//results.filter { !it.name.isNullOrBlank()}.sortedBy { it.id },
                        groups = manipulations.getGroupCount(results),//results.map {it.listId}.toSet().toList().sorted(),
                        selectedGroupIndex = initialSelectedGroupIndex
                    )
                }
            }
            .onError { error ->
                _state.update {
                  it.copy(
                      groupList = emptyList(),
                      isLoading = false,
                      errorMessage = error.toString(),
                      filteredGroupList = emptyList(),
                      groups = emptyList(),
                      selectedGroupIndex = emptyMap()
                  )
              }
            }
    }
    fun onAction(action: GroupListAction) { // expandable to add more functions to pass to components
        when (action) {
            is GroupListAction.onGroupListClick -> updateSelectedGroup(action.index)
        }

    }
    fun updateSelectedGroup(index: Int) { // to keep the list items open
        _state.update {
            it.copy(
                selectedGroupIndex = it.selectedGroupIndex.toMutableMap().apply {
                    this[index] = !(this[index] ?: false)
                }
            )
        }

    }
}