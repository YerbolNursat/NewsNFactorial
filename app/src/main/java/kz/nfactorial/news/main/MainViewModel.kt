package kz.nfactorial.news.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel() : ViewModel() {

    var mainState = mutableStateOf(
        MainState(
            searchText = "",
            columnData = emptyList(),
            rowData = emptyList()
        )
    )


    fun dispatch(event: MainEvent) {
        when (event) {

            MainEvent.OnAddClick -> {
                mainState.value = mainState.value.copy(columnData = mainState.value.columnData + NewsItem(mainState.value.searchText))
                mainState.value = mainState.value.copy(rowData = mainState.value.rowData + NewsItem(mainState.value.searchText))
            }

            is MainEvent.OnSearchTextChange -> {
                mainState.value = mainState.value.copy(searchText = event.text)
            }

        }
    }

}