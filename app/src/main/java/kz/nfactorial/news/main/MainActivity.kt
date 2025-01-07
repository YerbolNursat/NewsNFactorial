package kz.nfactorial.news.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState

const val DOMAIN_ROUTE = "https://www.nfactorial.school/"

class MainActivity() : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(
                onEvent = { event -> mainViewModel.dispatch(event) },
                state = mainViewModel.mainState.collectAsState()
            )
        }
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.setContext(this)
        mainViewModel.dispatch(MainEvent.OnGetNews)
    }
}

