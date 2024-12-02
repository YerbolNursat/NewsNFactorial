package kz.nfactorial.news.splash

import android.app.Activity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import kz.nfactorial.news.R

class SplashActivity : FragmentActivity(R.layout.splash_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        val splashPreference = SplashPreference(this.applicationContext)
        splashPreference.setTitle("title")
        splashPreference.setSubTitle("subTitle")

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        navController.setGraph(R.navigation.splash_graph)
    }
}