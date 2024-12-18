package kz.nfactorial.news.splash

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.nfactorial.news.db.dao.NewsDao
import kz.nfactorial.news.db.entity.NewsRoomDTO
import kz.nfactorial.news.main.MainActivity
import kotlin.random.Random

class SplashViewModel(
    newsDao: NewsDao,
) : ViewModel() {

    init {
        viewModelScope.launch {
            newsDao.clearNews()
            val random = Random(1000)
            for (i in 0..6) {
                newsDao.insertNews(
                    NewsRoomDTO(
                        imageSrc = 12,
                        title = random.nextInt(1000).toString(),
                        subTitle = random.nextInt(1000).toString()
                    )
                )
            }
        }
    }


    fun dispatch(
        event: SplashEvent,
        context: Context? = null
    ) {
        when (event) {
            is SplashEvent.OnClickToMain -> {
                val intent = Intent(context, MainActivity::class.java).apply {
                    putExtra(ARGS, event.args)
                }
                context?.startActivity(intent)
            }

            SplashEvent.OnResume -> {

            }
        }
    }
}