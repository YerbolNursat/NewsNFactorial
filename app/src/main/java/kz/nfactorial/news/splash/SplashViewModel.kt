package kz.nfactorial.news.splash

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import kz.nfactorial.news.main.MainActivity

class SplashViewModel : ViewModel() {

    fun dispatch(
        event: SplashEvent,
        context: Context
    ) {
        when(event){
            is SplashEvent.OnClickToMain -> {
                val intent = Intent(context, MainActivity::class.java).apply {
                    putExtra(ARGS, event.args)
                }
                context.startActivity(intent)
            }
        }
    }
}