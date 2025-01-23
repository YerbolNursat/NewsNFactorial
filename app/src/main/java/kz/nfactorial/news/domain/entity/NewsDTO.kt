package kz.nfactorial.news.domain.entity

data class NewsDTO(
    val newsItemDTO: List<NewsItemDTO>
)

data class NewsItemDTO(
    val id: Int,

    val image: String,

    val subTitle: String? = null,

    val category: String? = null,

    val author: String? = null,

    val readTime: String? = null,

    val title: String
)