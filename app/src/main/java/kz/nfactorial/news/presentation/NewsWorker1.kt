package kz.nfactorial.news.presentation

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NewsWorker1(
    context: Context,
    workerParameters: WorkerParameters
) : Worker(context, workerParameters) {

    var timer = 0

    @OptIn(DelicateCoroutinesApi::class)
    override fun doWork() = try {
        println("NewsWorker1 started")
        GlobalScope.launch {
            while (true) {
                delay(1000L)
                println("NewsWorker1 started ${timer++} seconds")
            }
        }

        Result.success()
    } catch (e: Exception) {
        Result.failure()
    }

    override fun onStopped() {
        super.onStopped()
        println("NewsWorker1 stopped")
    }
}