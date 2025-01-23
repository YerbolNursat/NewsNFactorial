package kz.nfactorial.news.presentation.main.event

sealed interface MainEvent {

    data class OnSearchTextChange(val text: String) : MainEvent

    object OnAddClick : MainEvent

    object OnLoading : MainEvent

    object OnGetNews : MainEvent

}