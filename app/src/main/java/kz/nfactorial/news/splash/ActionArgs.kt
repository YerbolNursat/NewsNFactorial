package kz.nfactorial.news.splash

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ActionArgs(
    val text: String
) : Parcelable