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
                            title = "A Strategic Analysis of Apple Inc.",
                            imageUrl = "https://media.licdn.com/dms/image/C4D12AQFNv_KSo_VCwQ/article-cover_image-shrink_600_2000/0/1638142508773?e=2147483647&v=beta&t=SoxCwfG_3-FF8YnKRQNmBv0k0zOPe26PI6-1Nda-GrE",
                            imageWidth = 740,
                            imageHeight = 415,
                            thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTpOBvEgzRNu284eO7Mw-_IKukYnD2CXhGMTs1rjPcJj45uRiyr&s",
                            thumbnailWidth = 300,
                            thumbnailHeight = 168,
                            source = "LinkedIn",
                            domain = "www.linkedin.com",
                            link = "https://www.linkedin.com/pulse/strategic-analysis-apple-inc-bidemi-ogedengbe",
                            googleUrl = "https://www.google.com/imgres?imgurl=https%3A%2F%2Fmedia.licdn.com%2Fdms%2Fimage%2FC4D12AQFNv_KSo_VCwQ%2Farticle-cover_image-shrink_600_2000%2F0%2F1638142508773%3Fe%3D2147483647%26v%3Dbeta%26t%3DSoxCwfG_3-FF8YnKRQNmBv0k0zOPe26PI6-1Nda-GrE&tbnid=E8hnCY8LIxTZ3M&imgrefurl=https%3A%2F%2Fwww.linkedin.com%2Fpulse%2Fstrategic-analysis-apple-inc-bidemi-ogedengbe&docid=gP0JwewjX407kM&w=740&h=415&ved=0ahUKEwiKpM2crKuGAxUXFFkFHSEGDwYQvFcIAigA",
                            position = 1,
                        ),
                        ImageDto(
                            title = "Company Overview of Apple & Intel: Philosophy, Portfolio, etc",
                            imageUrl = "https://thebrandhopper.com/wp-content/uploads/2023/11/apple-success-story-1024x576.jpg",
                            imageWidth = 1024,
                            imageHeight = 576,
                            thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcLsYkwuPnA4XE5yssV_NEbFf2BXqLXtb3mi-rnC95Htyssxg&s",
                            thumbnailWidth = 284,
                            thumbnailHeight = 177,
                            source = "www.toppr.com",
                            domain = "www.toppr.com",
                            link = "https://www.toppr.com/guides/commercial-knowledge/business-organizations/company-overview-of-apple-intel/",
                            googleUrl = "https://www.google.com/imgres?imgurl=https%3A%2F%2Fakm-img-a-in.tosshub.com%2Findiatoday%2Fapple_647_040116105516.jpg%3F.xccL1E_SxXCNt9K1Jjgg.vKCdo0Mf0u&tbnid=oLut_EKUPhhwyM&imgrefurl=https%3A%2F%2Fwww.toppr.com%2Fguides%2Fcommercial-knowledge%2Fbusiness-organizations%2Fcompany-overview-of-apple-intel%2F&docid=bM9CtTTSOxggsM&w=647&h=404&ved=0ahUKEwiKpM2crKuGAxUXFFkFHSEGDwYQvFcIAygB",
                            position = 1,
                        ),
                        /*ImageDto(
                            title = "",
                            imageUrl = "",
                            imageWidth = ,
                            imageHeight = ,
                            thumbnailUrl = "",
                            thumbnailWidth = ,
                            thumbnailHeight = ,
                            source = "",
                            domain = "",
                            link = "",
                            googleUrl = "",
                            position = 1,
                        ),*/
                    )
                } else {
                    listOf()
                }
            }
        }
}