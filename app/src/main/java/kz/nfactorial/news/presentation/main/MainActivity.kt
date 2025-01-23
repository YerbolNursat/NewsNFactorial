package kz.nfactorial.news.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import kz.nfactorial.news.presentation.main.view.MainScreen
import kz.nfactorial.news.presentation.main.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

const val DOMAIN_ROUTE = "https://www.nfactorial.school/"

class MainActivity() : ComponentActivity() {

    private val mainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen(
                onEvent = { event -> mainViewModel.dispatch(event) },
                state = mainViewModel.mainState.collectAsState()
            )
        }
    }

}

