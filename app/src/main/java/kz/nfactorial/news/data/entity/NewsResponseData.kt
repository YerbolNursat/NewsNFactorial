package kz.nfactorial.news.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kz.nfactorial.news.domain.entity.NewsItemDTO

@Serializable
data class NewsResponse(
    @SerialName("items")
    val newsItemResponse: List<NewsItemResponse>
)

@Serializable
data class NewsItemResponse(
    @SerialName("id")
    val id: Int,

    @SerialName("image")
    val image: String,

    @SerialName("subTitle")
    val subTitle: String? = null,

    @SerialName("category")
    val category: String? = null,

    @SerialName("author")
    val author: String? = null,

    @SerialName("readTime")
    val readTime: String? = null,

    @SerialName("title")
    val title: String
)

fun NewsItemResponse.mapToNewsItemDTO() = NewsItemDTO(
    id = this.id,
    image = this.image,
    subTitle = this.subTitle,
    category = this.category,
    author = this.author,
    readTime = this.readTime,
    title = this.title
)