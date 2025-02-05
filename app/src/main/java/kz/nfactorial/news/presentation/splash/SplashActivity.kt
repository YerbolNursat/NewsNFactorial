package kz.nfactorial.news.presentation.splash

import android.app.ComponentCaller
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import kz.nfactorial.news.R

class SplashActivity : FragmentActivity(R.layout.splash_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        val data: Uri? = intent?.data
        openSmth()

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        navController.setGraph(R.navigation.splash_graph)
        val intent = Intent()

    }

    override fun onNewIntent(intent: Intent, caller: ComponentCaller) {
        super.onNewIntent(intent, caller)
        intent.data
        openSmth()
    }
    private fun openSmth(){}

    private fun openActivity(
        bundle: Bundle? = null,
        action: String? = null
    ) {
        with(Intent()) {
            this.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            action?.let { this.action = it }
            bundle?.let { this.putExtras(bundle) }
//            setClassName(thiskz.technodom.airba.SplashActivity, MAIN_ACTIVITY_REFERENCE)
//            thiskz.technodom.airba.SplashActivity.startActivity(this)
        }
    }

}