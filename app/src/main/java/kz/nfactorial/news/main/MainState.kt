package kz.nfactorial.news.main

data class MainState(
    val searchText: String,
    val rowData: List<NewsItem>,
    val columnData: List<NewsItem>
)


data class NewsItem(
    val name: String
)
