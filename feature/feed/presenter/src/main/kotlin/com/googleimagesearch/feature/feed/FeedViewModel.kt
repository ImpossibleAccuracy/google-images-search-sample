package com.googleimagesearch.feature.feed

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.googleimagesearch.domain.model.SearchParameters
import com.googleimagesearch.domain.model.SearchResult
import com.googleimagesearch.domain.model.SearchType
import com.googleimagesearch.domain.payload.SearchDateRange
import com.googleimagesearch.domain.repository.SearchRepository
import com.googleimagesearch.feature.feed.viewmodel.FeedIntent
import com.googleimagesearch.feature.feed.viewmodel.FeedUiState
import com.googleimagesearch.navigation.screen.AppViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FeedViewModel(
    private val searchRepository: SearchRepository<SearchType.Images>,
) : AppViewModel() {
    companion object {
        private const val SEARCH_IDLE = 700L

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
    }

    fun onIntent(intent: FeedIntent) {
        when (intent) {
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

            FeedIntent.Search -> {
                startReloadJob()
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
            searchRepository
                .search<SearchResult.GoogleImage>(
                    parameters = defaultSearchParams.copy(
                        query = query,
                    )
                )
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
