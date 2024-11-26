package kz.nfactorial.news.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

const val DOMAIN_ROUTE = "https://www.nfactorial.school/"

class MainActivity() : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(
                onEvent = { event -> mainViewModel.dispatch(event) },
                state = mainViewModel.mainState.value
            )
        }
    }
}

