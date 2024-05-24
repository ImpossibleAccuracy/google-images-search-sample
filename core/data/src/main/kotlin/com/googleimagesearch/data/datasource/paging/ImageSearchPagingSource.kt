package com.googleimagesearch.data.datasource.paging

import com.googleimagesearch.data.datasource.declaration.ImageDataSource
import com.googleimagesearch.data.datasource.paging.base.BasePagingSource
import com.googleimagesearch.data.payload.ImageDto
import com.googleimagesearch.domain.model.SearchParameters
import com.googleimagesearch.domain.model.SearchResult
import com.googleimagesearch.domain.payload.SearchResultSource
import com.googleimagesearch.domain.payload.ImageSize

class ImageSearchPagingSource(
    private val params: SearchParameters.ImageSearchParams,
    private val dataSource: ImageDataSource.Remote,
) : BasePagingSource<SearchResult.GoogleImage, ImageDto>(
    dataFetcher = {
        dataSource.search(it, params)
    },
    dataMapper = {
        SearchResult.GoogleImage(
            title = it.title!!,
            imageUrl = it.imageUrl!!,
            imageSize = ImageSize(
                width = it.imageWidth!!,
                height = it.imageHeight!!,
            ),
            thumbnailUrl = it.thumbnailUrl!!,
            thumbnailSize = ImageSize(
                width = it.thumbnailWidth!!,
                height = it.thumbnailHeight!!,
            ),
            source = SearchResultSource(
                source = it.source!!,
                sourceDomain = it.domain!!,
                sourceLink = it.link!!,
                sourceGoogleLink = it.googleUrl!!,
            ),
        )
    }
)
