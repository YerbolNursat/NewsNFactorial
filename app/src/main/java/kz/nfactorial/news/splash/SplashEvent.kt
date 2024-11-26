package kz.nfactorial.news.splash

sealed interface SplashEvent {

    data class OnClickToMain(val args: ActionArgs) : SplashEvent

}