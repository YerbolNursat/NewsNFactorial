package kz.nfactorial.news.splash

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SplashPreference(
    context: Context
) {

    private val sharedPreferences =
        context.getSharedPreferences("splash_pref", Context.MODE_PRIVATE)


    fun getTitle() = sharedPreferences.getString("title", "") ?: ""

    fun setTitle(title: String) {
        sharedPreferences.edit().putString("title", title).apply()
    }

    fun getSubTitle() = sharedPreferences.getString("subTitle", "") ?: ""

    fun setSubTitle(subTitle: String) {
        sharedPreferences.edit().putString("subTitle", subTitle).apply()
    }


}