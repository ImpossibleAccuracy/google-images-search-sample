package com.googleimagesearch.data.datasource.impl.remote

import com.googleimagesearch.data.datasource.declaration.ImageDataSource
import com.googleimagesearch.data.payload.ImageDto
import com.googleimagesearch.domain.model.SearchParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pro.respawn.apiresult.ApiResult

class RemoteImageDataSourceImpl : ImageDataSource.Remote {
    override suspend fun search(page: Int, params: SearchParameters.ImageSearchParams): ApiResult<List<ImageDto>> =
        withContext(Dispatchers.IO) {
            ApiResult {
                if (page == 1) {
                    listOf(
                        ImageDto(
                            title = "Test",
                            imageUrl = "https://i.natgeofe.com/n/548467d8-c5f1-4551-9f58-6817a8d2c45e/NationalGeographic_2572187_square.jpg",
                            imageWidth = 3072,
                            imageHeight = 3072,
                            thumbnailUrl = "https://cdn.britannica.com/34/235834-050-C5843610/two-different-breeds-of-cats-side-by-side-outdoors-in-the-garden.jpg",
                            thumbnailWidth = 300,
                            thumbnailHeight = 168,
                            source = "Google images",
                            domain = "google.com",
                            link = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.britannica.com%2Fanimal%2Fcat&psig=AOvVaw0bp60TgbZB-yUguyhh7mxN&ust=1716625026592000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCJiIgNnspYYDFQAAAAAdAAAAABAc",
                            googleUrl = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.britannica.com%2Fanimal%2Fcat&psig=AOvVaw0bp60TgbZB-yUguyhh7mxN&ust=1716625026592000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCJiIgNnspYYDFQAAAAAdAAAAABAc",
                            position = 1,
                        ),
                        ImageDto(
                            title = "Test2",
                            imageUrl = "https://images.contentstack.io/v3/assets/blt6f84e20c72a89efa/bltd23d698154616afc/63c73a89a362596b80751065/img-cat-whiskers-101-header.jpg",
                            imageWidth = 3072,
                            imageHeight = 4000,
                            thumbnailUrl = "https://www.dreamiestreats.co.uk/sites/g/files/fnmzdf5196/files/2023-01/When-should-I-give-my-cat-catnip-detail.jpg",
                            thumbnailWidth = 275,
                            thumbnailHeight = 183,
                            source = "Google images",
                            domain = "google.com",
                            link = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.britannica.com%2Fanimal%2Fcat&psig=AOvVaw0bp60TgbZB-yUguyhh7mxN&ust=1716625026592000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCJiIgNnspYYDFQAAAAAdAAAAABAc",
                            googleUrl = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.britannica.com%2Fanimal%2Fcat&psig=AOvVaw0bp60TgbZB-yUguyhh7mxN&ust=1716625026592000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCJiIgNnspYYDFQAAAAAdAAAAABAc",
                            position = 1,
                        ),
                        ImageDto(
                            title = "Test123",
                            imageUrl = "https://www.dreamiestreats.co.uk/sites/g/files/fnmzdf5196/files/2023-01/When-should-I-give-my-cat-catnip-detail.jpg",
                            imageWidth = 4000,
                            imageHeight = 3072,
                            thumbnailUrl = "https://www.forbes.com/advisor/wp-content/uploads/2023/09/how-much-does-a-cat-cost.jpeg-900x510.jpg",
                            thumbnailWidth = 284,
                            thumbnailHeight = 177,
                            source = "Google images",
                            domain = "google.com",
                            link = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.britannica.com%2Fanimal%2Fcat&psig=AOvVaw0bp60TgbZB-yUguyhh7mxN&ust=1716625026592000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCJiIgNnspYYDFQAAAAAdAAAAABAc",
                            googleUrl = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.britannica.com%2Fanimal%2Fcat&psig=AOvVaw0bp60TgbZB-yUguyhh7mxN&ust=1716625026592000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCJiIgNnspYYDFQAAAAAdAAAAABAc",
                            position = 1,
                        ),
                        ImageDto(
                            title = "Test123",
                            imageUrl = "https://cdn.theatlantic.com/thumbor/d8lh_KAZuOgBYslMOP4T0iu9Fks=/0x62:2000x1187/1600x900/media/img/mt/2018/03/AP_325360162607/original.jpg",
                            imageWidth = 2000,
                            imageHeight = 2500,
                            thumbnailUrl = "https://cdn.britannica.com/39/226539-050-D21D7721/Portrait-of-a-cat-with-whiskers-visible.jpg",
                            thumbnailWidth = 300,
                            thumbnailHeight = 168,
                            source = "Google images",
                            domain = "google.com",
                            link = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.britannica.com%2Fanimal%2Fcat&psig=AOvVaw0bp60TgbZB-yUguyhh7mxN&ust=1716625026592000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCJiIgNnspYYDFQAAAAAdAAAAABAc",
                            googleUrl = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.britannica.com%2Fanimal%2Fcat&psig=AOvVaw0bp60TgbZB-yUguyhh7mxN&ust=1716625026592000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCJiIgNnspYYDFQAAAAAdAAAAABAc",
                            position = 1,
                        ),
                    )
                } else {
                    listOf()
                }
            }
        }
}