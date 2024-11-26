package kz.nfactorial.news.main

sealed interface MainEvent {

    data class OnSearchTextChange(val text: String) : MainEvent

    object OnAddClick : MainEvent

}