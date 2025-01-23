package kz.nfactorial.news.presentation.splash

sealed interface SplashEvent {

    data class OnClickToMain(val args: ActionArgs) : SplashEvent

    object  OnResume : SplashEvent

}