package kz.nfactorial.news.splash

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import kz.nfactorial.news.db.dao.NewsDao
import kz.nfactorial.news.db.entity.NewsDb
import kz.nfactorial.news.main.MainActivity
import kz.nfactorial.news.R

class SplashViewModel(
    newsDao: NewsDao,
    splashPreference: SplashPreference
) : ViewModel() {

    init {
        newsDao.insertAccount(
            newsDb = NewsDb(
                imageSrc = R.drawable.news,
                title = "title",
                subTitle = "subtitle",
                id = 10
            )
        )

        println("title is ${splashPreference.getTitle()}")
        println("subtitle is ${splashPreference.getSubTitle()}")
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