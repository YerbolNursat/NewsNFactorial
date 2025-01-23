package kz.nfactorial.news.presentation.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel

const val ARGS = "ACTION_ARGS"

class SplashFragment : Fragment() {

    private val splashViewModel by viewModel<SplashViewModel>()

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