package kz.nfactorial.news.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

const val ARGS = "ACTION_ARGS"

class SplashFragment : Fragment() {

    private val splashViewModel by viewModels<SplashViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {

            SplashScreen(
                onEvent = { event -> splashViewModel.dispatch(event, requireActivity()) }
            )

        }
    }
}