package kz.nfactorial.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kz.nfactorial.news.main.MainScreen
import kz.nfactorial.news.splash.ARGS
import kz.nfactorial.news.splash.ActionArgs

const val DOMAIN_ROUTE = "https://www.nfactorial.school/"

class MainActivity() : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val args = intent.getParcelableExtra<ActionArgs>(ARGS)!!

            MainScreen(args.text)
        }
    }
}

