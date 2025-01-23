package kz.nfactorial.news.data.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kz.nfactorial.news.data.api.NewsApi
import kz.nfactorial.news.data.db.dao.NewsDao
import kz.nfactorial.news.data.db.entity.NewsColumnRoomDTO
import kz.nfactorial.news.data.db.entity.NewsRowRoomDTO
import kz.nfactorial.news.data.entity.mapToNewsItemDTO
import kz.nfactorial.news.domain.entity.NewsDTO
import kz.nfactorial.news.domain.entity.NewsItemDTO
import kz.nfactorial.news.domain.entity.NewsType
import kz.nfactorial.news.domain.repository.NewsRepository
import java.util.Locale

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
) : NewsRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getNews(type: NewsType): Flow<NewsDTO> {
        return flow {
            val list = when (type) {
                NewsType.ROW -> newsDao.getRowNews().map {
                    NewsItemDTO(
                        id = it.id,
                        image = it.image,
                        title = it.title,
                        subTitle = it.subTitle
                    )
                }

                NewsType.COLUMN -> newsDao.getColumnNews().map {
                    NewsItemDTO(
                        id = it.id,
                        image = it.image,
                        title = it.title,
                        category = it.category,
                        author = it.author,
                        readTime = it.readTime
                    )
                }
            }
            emit(NewsDTO(list))
        }
            .flatMapConcat { roomDTO ->
                flow {
                    emit(roomDTO)
                    val response = newsApi.getNews(type.name.toLowerCase(Locale.getDefault()))
                    response.onSuccess { newsResponse ->
                        when (type) {
                            NewsType.ROW -> {
                                newsDao.clearRowNews()
                                newsResponse.newsItemResponse.map {
                                    NewsRowRoomDTO(
                                        id = it.id,
                                        title = it.title,
                                        subTitle = it.subTitle.orEmpty(),
                                        image = it.image
                                    )
                                }.onEach {
                                    newsDao.insertRowNews(it)
                                }
                            }

                            NewsType.COLUMN -> {
                                newsDao.clearColumnNews()
                                newsResponse.newsItemResponse.map {
                                    NewsColumnRoomDTO(
                                        id = it.id,
                                        title = it.title,
                                        image = it.image,
                                        category = it.category.orEmpty(),
                                        author = it.author.orEmpty(),
                                        readTime = it.readTime.orEmpty()
                                    )
                                }.onEach {
                                    newsDao.insertColumnNews(it)
                                }
                            }
                        }
                        emit(NewsDTO(newsResponse.newsItemResponse.map { it.mapToNewsItemDTO() }))
                    }
                }
            }
    }


}
