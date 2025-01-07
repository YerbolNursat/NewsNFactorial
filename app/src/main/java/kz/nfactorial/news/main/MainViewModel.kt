package kz.nfactorial.news.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kz.nfactorial.news.NetworkApi
import kz.nfactorial.news.data.api.NewsApi
import kz.nfactorial.news.data.entity.NewsType
import kz.nfactorial.news.data.repository.NewsRepository
import kz.nfactorial.news.db.DatabaseHolder

class MainViewModel() : ViewModel() {

    lateinit var repository: NewsRepository

    fun setContext(context: Context) {
        this.repository = NewsRepository(
            newsApi = NetworkApi().retrofit.create<NewsApi>(
                NewsApi::class.java
            ),
            newsDao = DatabaseHolder.getOrCreate(context).getAccountDao()
        )
    }


    var mainState = MutableStateFlow(
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
                    repository.getNews(NewsType.ROW).collect {
                        mainState.value =
                            mainState.value.copy(rowData = RowUIState.OnGetNews(it.itemDTOS.map {
                                RowNewsItem(
                                    title = it.title,
                                    subTitle = it.subTitle.orEmpty(),
                                    imageSrc = it.image
                                )
                            }))
                    }


                    repository.getNews(NewsType.COLUMN).collect {
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