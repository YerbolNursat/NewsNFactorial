package kz.nfactorial.news.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kz.nfactorial.news.domain.entity.NewsType
import kz.nfactorial.news.domain.usecase.GetNewsUseCase
import kz.nfactorial.news.presentation.main.event.MainEvent
import kz.nfactorial.news.presentation.main.model.ColumnNewsItem
import kz.nfactorial.news.presentation.main.model.RowNewsItem
import kz.nfactorial.news.presentation.main.state.ColumnUIState
import kz.nfactorial.news.presentation.main.state.MainState
import kz.nfactorial.news.presentation.main.state.RowUIState

class MainViewModel(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

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
                    getNewsUseCase.execute(NewsType.ROW).collect {
                        mainState.value =
                            mainState.value.copy(rowData = RowUIState.OnGetNews(it.newsItemDTO.map {
                                RowNewsItem(
                                    title = it.title,
                                    subTitle = it.subTitle.orEmpty(),
                                    imageSrc = it.image
                                )
                            }))
                    }


                    getNewsUseCase.execute(NewsType.COLUMN).collect {
                        mainState.value =
                            mainState.value.copy(columnData = ColumnUIState.OnGetNews(it.newsItemDTO.map {
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