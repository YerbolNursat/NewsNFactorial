package kz.nfactorial.news.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.nfactorial.news.NetworkApi
import kz.nfactorial.news.data.api.NewsApi
import kz.nfactorial.news.data.repository.NewsRepository

class MainViewModel() : ViewModel() {

    val repository = NewsRepository(
        newsApi = NetworkApi().retrofit.create<NewsApi>(
            NewsApi::class.java
        )
    )

    var mainState = mutableStateOf(
        MainState(
            searchText = "",
            columnData = ColumnUIState.OnLoading,
            rowData = RowUIState.OnLoading
        )
    )


    fun dispatch(event: MainEvent) {
        when (event) {

            MainEvent.OnAddClick -> {

            }

            is MainEvent.OnSearchTextChange -> {
                mainState.value = mainState.value.copy(searchText = event.text)
            }

            MainEvent.OnLoading -> mainState.value =
                mainState.value.copy(columnData = ColumnUIState.OnLoading)

            MainEvent.OnGetNews -> {
                viewModelScope.launch {
                    val rowData = repository.getNews("row")
                    rowData.onSuccess {
                        mainState.value =
                            mainState.value.copy(rowData = RowUIState.OnGetNews(it.itemDTOS.map {
                                RowNewsItem(
                                    title = it.title,
                                    subTitle = it.subTitle.orEmpty(),
                                    imageSrc = it.image
                                )
                            }))

                    }
                    val columnData = repository.getNews("column")
                    columnData.onSuccess {
                        mainState.value =
                            mainState.value.copy(columnData = ColumnUIState.OnGetNews(it.itemDTOS.map {
                                ColumnNewsItem(
                                    title = it.title,
                                    category = it.category.orEmpty(),
                                    author = it.author.orEmpty(),
                                    readTime = it.readTime.orEmpty(),
                                    image = it.image,
                                )
                            }))
                    }

                }
            }
        }
    }


}