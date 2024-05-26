package com.googleimagesearch.feature.feed

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.googleimagesearch.domain.model.SearchParameters
import com.googleimagesearch.domain.model.SearchResult
import com.googleimagesearch.domain.payload.SearchDateRange
import com.googleimagesearch.domain.usecase.SearchImagesUseCase
import com.googleimagesearch.feature.feed.domain.usecase.AddSearchHistoryItemUseCase
import com.googleimagesearch.feature.feed.domain.usecase.GetSearchHistoryUseCase
import com.googleimagesearch.feature.feed.viewmodel.FeedIntent
import com.googleimagesearch.feature.feed.viewmodel.FeedUiState
import com.googleimagesearch.navigation.screen.AppViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FeedViewModel(
    private val getSearchHistoryUseCase: GetSearchHistoryUseCase,
    private val addSearchHistoryItemUseCase: AddSearchHistoryItemUseCase,
    private val searchImagesUseCase: SearchImagesUseCase,
) : AppViewModel() {
    companion object {
        private val defaultSearchParams = SearchParameters.ImageSearchParams(
            query = "",
            country = null,
            location = null,
            language = null,
            dateRange = SearchDateRange.AnyTime,
            autocorrect = true,
        )
    }

    private val _uiState = MutableStateFlow(FeedUiState())
    val uiState = _uiState.asStateFlow()

    private val _searchResults =
        MutableStateFlow<PagingData<SearchResult.GoogleImage>>(PagingData.empty())
    val searchResults = _searchResults.asStateFlow()

    private var reloadJob: Job? = null

    init {
        reloadJob = viewModelScope.launch {
            loadSearchResults()
        }

        viewModelScope.launch {
            getSearchHistoryUseCase()
                .collect { items ->
                    _uiState.update {
                        it.copy(searchHistory = items)
                    }
                }
        }
    }

    fun onIntent(intent: FeedIntent) {
        when (intent) {
            FeedIntent.Search -> {
                viewModelScope.launch {
                    addSearchHistoryItemUseCase(uiState.value.query)
                }

                startReloadJob()
            }

            is FeedIntent.SearchByHistoryItem -> {
                _uiState.update {
                    it.copy(
                        query = intent.historyItem.text,
                    )
                }

                startReloadJob()
            }

            is FeedIntent.UpdateQuery -> {
                _uiState.update {
                    it.copy(
                        query = intent.query
                    )
                }
            }

            FeedIntent.ClearQuery -> {
                _searchResults.value = PagingData.empty()

                _uiState.update {
                    it.copy(
                        isSearchResultInit = false,
                        query = "",
                    )
                }
            }
        }
    }

    private fun startReloadJob(force: Boolean = false) {
        if (force) {
            reloadJob = viewModelScope.launch {
                loadSearchResults()
            }
        } else {
            reloadJob?.cancel()

            reloadJob = viewModelScope.launch {
//                delay(SEARCH_IDLE)

                loadSearchResults()
            }
        }
    }

    private suspend fun loadSearchResults() {
        val query = uiState.value.query

        if (query.isNotBlank()) {
            searchImagesUseCase(defaultSearchParams.copy(query = query))
                .flowOn(Dispatchers.IO)
                .cachedIn(viewModelScope)
                .collect { pagingData ->
                    _searchResults.value = pagingData

                    _uiState.update {
                        it.copy(isSearchResultInit = true)
                    }
                }
        } else {
            _searchResults.value = PagingData.empty()

            _uiState.update {
                it.copy(isSearchResultInit = false)
            }
        }
    }
}
