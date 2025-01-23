package kz.nfactorial.news.domain.repository

import kotlinx.coroutines.flow.Flow
import kz.nfactorial.news.domain.entity.NewsDTO
import kz.nfactorial.news.domain.entity.NewsType

interface NewsRepository {

    fun getNews(type: NewsType): Flow<NewsDTO>

}