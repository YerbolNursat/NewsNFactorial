package kz.nfactorial.news.presentation

import android.app.Service
import android.content.Intent
import android.os.IBinder
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NewsService : Service() {
    var timer = 0

    override fun onBind(intent: Intent?): IBinder? {

        return null
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate() {
        super.onCreate()
        println("NewsService started")
        GlobalScope.launch {
            while (true) {
                delay(1000L)
                println("NewsService started ${timer++} seconds")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        println("NewsService destroyed")
    }
}