package kz.nfactorial.news.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import kz.nfactorial.news.db.DatabaseHolder
import kz.nfactorial.news.ext.viewModels
import kz.nfactorial.news.splash.SplashViewModel

const val DOMAIN_ROUTE = "https://www.nfactorial.school/"

class MainActivity() : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(
                onEvent = { event -> mainViewModel.dispatch(event) },
                state = mainViewModel.mainState.value
            )
        }
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.initDao(DatabaseHolder.getOrCreate(this.applicationContext).getAccountDao())
        mainViewModel.dispatch(MainEvent.OnGetNews)
    }
}

