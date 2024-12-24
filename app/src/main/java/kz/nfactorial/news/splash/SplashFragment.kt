package kz.nfactorial.news.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import kz.nfactorial.news.NetworkApi
import kz.nfactorial.news.data.api.NewsApi
import kz.nfactorial.news.data.repository.NewsRepository
import kz.nfactorial.news.ext.viewModels

const val ARGS = "ACTION_ARGS"

class SplashFragment : Fragment() {

    private val splashViewModel: SplashViewModel by viewModels(
        viewModelInitializer = {
            SplashViewModel(
                newsRepository = NewsRepository(
                    newsApi = NetworkApi().retrofit.create<NewsApi>(
                        NewsApi::class.java
                    )
                ),
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