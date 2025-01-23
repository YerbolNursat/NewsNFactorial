package kz.nfactorial.news.presentation

import android.app.Application
import android.content.Intent
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import kz.nfactorial.news.data.db.dbModule
import kz.nfactorial.news.data.network.networkModule
import kz.nfactorial.news.presentation.di.newsModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.util.concurrent.TimeUnit

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(networkModule)
            modules(dbModule)
            modules(newsModules)
        }
        startService(Intent(this, NewsService::class.java))

        val oneTimeRequest = OneTimeWorkRequest.Builder(NewsWorker::class.java)
            .build()
        val constraints = Constraints(
            requiredNetworkType = NetworkType.CONNECTED,
            requiresCharging = true
        )
        val periodicTimeRequest = PeriodicWorkRequestBuilder<NewsWorker1>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .setInitialDelay(10, TimeUnit.SECONDS)
            .build()
        val workManager = WorkManager.getInstance(applicationContext)
        workManager.enqueue(oneTimeRequest)
        workManager.enqueue(periodicTimeRequest)

    }
}