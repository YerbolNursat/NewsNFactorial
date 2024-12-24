package kz.nfactorial.news.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsRowDTO(
    @SerialName("items")
    val itemDTOS: List<ItemDTO>
)

@Serializable
data class ItemDTO(
    @SerialName("id")
    val id: String,

    @SerialName("image")
    val image: String,

    @SerialName("subTitle")
    val subTitle: String? =null,

    @SerialName("category")
    val category: String? =null,

    @SerialName("author")
    val author: String? =null,

    @SerialName("readTime")
    val readTime: String? =null,

    @SerialName("title")
    val title: String
)