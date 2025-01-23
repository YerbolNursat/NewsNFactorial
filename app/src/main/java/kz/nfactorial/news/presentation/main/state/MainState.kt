package kz.nfactorial.news.presentation.main.state

import kz.nfactorial.news.presentation.main.model.ColumnNewsItem
import kz.nfactorial.news.presentation.main.model.RowNewsItem


data class MainState(
    val searchText: String,
    val rowData: RowUIState,
    val columnData: ColumnUIState
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

