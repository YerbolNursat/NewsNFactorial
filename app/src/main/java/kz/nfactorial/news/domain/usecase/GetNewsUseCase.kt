package kz.nfactorial.news.domain.usecase

import kotlinx.coroutines.flow.Flow
import kz.nfactorial.news.domain.entity.NewsDTO
import kz.nfactorial.news.domain.entity.NewsType
import kz.nfactorial.news.domain.repository.NewsRepository

interface GetNewsUseCase {

    fun execute(type: NewsType): Flow<NewsDTO>
}

class GetNewsUseCaseImpl(
    private val newsRepository: NewsRepository
) : GetNewsUseCase {

    override fun execute(type: NewsType) = newsRepository.getNews(type)

}