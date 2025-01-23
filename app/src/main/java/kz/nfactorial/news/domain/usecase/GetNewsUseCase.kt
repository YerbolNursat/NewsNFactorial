package kz.nfactorial.news.domain.usecase

import kz.nfactorial.news.domain.entity.NewsType
import kz.nfactorial.news.domain.repository.NewsRepository

class GetNewsUseCase(
    private val newsRepository: NewsRepository
) {

    fun execute(type: NewsType) = newsRepository.getNews(type)

}