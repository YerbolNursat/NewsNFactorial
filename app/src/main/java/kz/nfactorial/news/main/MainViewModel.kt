package kz.nfactorial.news.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kz.nfactorial.news.db.dao.NewsDao

class MainViewModel() : ViewModel() {

    lateinit var newsDao: NewsDao

    fun initDao(newsDao: NewsDao) {
        this.newsDao = newsDao
    }

    var mainState = mutableStateOf(
        MainState(
            searchText = "",
            columnData = UIState.OnLoading,
            rowData = emptyList()
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
                mainState.value.copy(columnData = UIState.OnLoading)

            MainEvent.OnGetNews -> {
                viewModelScope.launch {
                    delay(3000L)
                    val newsRoomDTOs = newsDao.getNews()
                    val newsUiList = newsRoomDTOs.map {
                        NewsItem(
                            name = it.title
                        )
                    }

                    mainState.value =
                        mainState.value.copy(columnData = UIState.OnGetNews(newsUiList))
                }
            }
        }
    }


}