package kz.nfactorial.news.stubs

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kz.nfactorial.news.domain.entity.NewsDTO
import kz.nfactorial.news.domain.entity.NewsItemDTO
import kz.nfactorial.news.domain.entity.NewsType
import kz.nfactorial.news.domain.usecase.GetNewsUseCase

class GetNewsUseCaseStub(
    private val rowData: List<NewsItemDTO> = emptyList(),
    private val columnData: List<NewsItemDTO> = emptyList(),
): GetNewsUseCase {

    override fun execute(type: NewsType): Flow<NewsDTO> {
        return flowOf(
            NewsDTO(
                when (type) {
                    NewsType.ROW -> rowData
                    NewsType.COLUMN -> columnData
                }
            )
        )
    }
}