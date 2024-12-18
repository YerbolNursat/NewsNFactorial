package kz.nfactorial.news.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import kotlinx.coroutines.Delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kz.nfactorial.news.db.DatabaseHolder
import kz.nfactorial.news.ext.viewModels

const val ARGS = "ACTION_ARGS"

class SplashFragment : Fragment() {

    private val splashViewModel: SplashViewModel by viewModels(
        viewModelInitializer = {
            SplashViewModel(
                newsDao = DatabaseHolder.getOrCreate(requireContext().applicationContext)
                    .getAccountDao(),
            )
        }
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            SplashScreen(
                onEvent = { event -> splashViewModel.dispatch(event, requireActivity()) },
            )
        }
    }

    override fun onResume() {
        super.onResume()
        splashViewModel.dispatch(SplashEvent.OnResume)
    }
}