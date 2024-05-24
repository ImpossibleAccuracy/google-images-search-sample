package com.googleimagesearch.data.datasource.impl.remote

import com.googleimagesearch.data.Constants
import com.googleimagesearch.data.datasource.declaration.ImageDataSource
import com.googleimagesearch.data.payload.ImageDto
import com.googleimagesearch.domain.model.SearchParameters
import kotlinx.coroutines.delay
import pro.respawn.apiresult.ApiResult

class RemoteImageDataSourceImpl : ImageDataSource.Remote {
    override suspend fun search(page: Int, params: SearchParameters.ImageSearchParams): ApiResult<List<ImageDto>> =
        ApiResult {
            delay(2000)

            if (page <= Constants.PREFETCH_DISTANCE * 2) {
                (0..Constants.MAX_PAGE_SIZE).map {
                    ImageDto(
                        title = "Test",
                        imageUrl = "https://i.natgeofe.com/n/548467d8-c5f1-4551-9f58-6817a8d2c45e/NationalGeographic_2572187_square.jpg",
                        imageWidth = 3072,
                        imageHeight = 3072,
                        thumbnailUrl = "https://cdn.britannica.com/34/235834-050-C5843610/two-different-breeds-of-cats-side-by-side-outdoors-in-the-garden.jpg",
                        thumbnailWidth = 1600,
                        thumbnailHeight = 1054,
                        source = "Google images",
                        domain = "google.com",
                        link = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.britannica.com%2Fanimal%2Fcat&psig=AOvVaw0bp60TgbZB-yUguyhh7mxN&ust=1716625026592000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCJiIgNnspYYDFQAAAAAdAAAAABAc",
                        googleUrl = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.britannica.com%2Fanimal%2Fcat&psig=AOvVaw0bp60TgbZB-yUguyhh7mxN&ust=1716625026592000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCJiIgNnspYYDFQAAAAAdAAAAABAc",
                        position = 1,
                    )
                }
            } else {
                listOf()
            }
        }
}