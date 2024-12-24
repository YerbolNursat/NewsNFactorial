package kz.nfactorial.news.main


data class MainState(
    val searchText: String,
    val rowData: RowUIState,
    val columnData: ColumnUIState
)

data class RowNewsItem(
    val imageSrc: String,
    val title: String,
    val subTitle: String
)

data class ColumnNewsItem(
    val category: String,
        val author: String,
    val readTime: String,
    val image: String,
    val title: String
)

interface ColumnUIState {

    val news: List<ColumnNewsItem>

    object OnLoading : ColumnUIState {
        override val news: List<ColumnNewsItem> = emptyList<ColumnNewsItem>()
    }

    data class OnGetNews(
        override val news: List<ColumnNewsItem>
    ) : ColumnUIState
}


interface RowUIState {

    val news: List<RowNewsItem>

    object OnLoading : RowUIState {
        override val news: List<RowNewsItem> = emptyList<RowNewsItem>()
    }

    data class OnGetNews(
        override val news: List<RowNewsItem>
    ) : RowUIState
}

