package kz.nfactorial.news.splash

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.nfactorial.news.data.params.NewsParams
import kz.nfactorial.news.data.repository.NewsRepository
import kz.nfactorial.news.main.MainActivity

class SplashViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {


    fun dispatch(
        event: SplashEvent,
        context: Context? = null
    ) {
        when (event) {
            is SplashEvent.OnClickToMain -> {
                viewModelScope.launch {
                    val result = newsRepository.addNews(NewsParams(1))
                    result.onSuccess {
                        println(it)
                            val intent = Intent(context, MainActivity::class.java).apply {
                                putExtra(ARGS, event.args)
                            }
                            context?.startActivity(intent)
                        }
                        .onFailure {
                            println(it)

                        }
                }
            }

            SplashEvent.OnResume -> {

            }
        }
    }
}