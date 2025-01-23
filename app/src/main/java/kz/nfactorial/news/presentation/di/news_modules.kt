package kz.nfactorial.news.presentation.di

import kz.nfactorial.news.data.api.NewsApi
import kz.nfactorial.news.data.db.NewsDatabase
import kz.nfactorial.news.data.repository.NewsRepositoryImpl
import kz.nfactorial.news.domain.repository.NewsRepository
import kz.nfactorial.news.domain.usecase.GetNewsUseCase
import kz.nfactorial.news.presentation.main.viewmodel.MainViewModel
import kz.nfactorial.news.presentation.splash.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

private fun getNewsApi(retrofit: Retrofit) = retrofit.create<NewsApi>(NewsApi::class.java)

private fun getNewsDao(database: NewsDatabase) = database.getAccountDao()

val newsModules = module {

    single<NewsRepository> { NewsRepositoryImpl(getNewsApi(get()), getNewsDao(get())) }
    factory { GetNewsUseCase(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { SplashViewModel() }
}