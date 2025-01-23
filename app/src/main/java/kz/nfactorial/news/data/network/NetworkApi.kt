package kz.nfactorial.news.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val networkModule = module {

    factory { getOkhttpClient() }
    factory { getRetrofit(get(), get()) }
    single { getJson() }
}

private fun getJson() = Json { encodeDefaults = true }

private fun getOkhttpClient() = OkHttpClient
    .Builder()
    .readTimeout(60_000, TimeUnit.MILLISECONDS)
    .writeTimeout(60_000, TimeUnit.MILLISECONDS)
    .build()


private fun getRetrofit(
    okHttpClient: OkHttpClient,
    json: Json
) = Retrofit.Builder()
    .baseUrl("https://nfactorial.onrender.com/")
    .client(okHttpClient)
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .addCallAdapterFactory(ResultCallAdapterFactory.create())
    .build()


