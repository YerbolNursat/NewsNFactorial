package kz.nfactorial.news.splash

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kz.nfactorial.news.MainActivity
import kz.nfactorial.news.splash.SplashScreen
import kz.nfactorial.news.R

const val ARGS = "ACTION_ARGS"

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        val navController = findNavController()

        setContent {

            val intent = Intent(requireActivity(), MainActivity::class.java).apply {
                putExtra(ARGS, ActionArgs("We are from Splash Fragment"))
            }
            intent.data
//            SplashScreen { requireActivity().startActivity(intent) }
            SplashScreen { navController.navigate(R.id.action_splashFragment_to_splashFragment2,
//                bundleOf(ARGS to ActionArgs("We are from Splash Fragment"))
            ) }
        }
    }
}