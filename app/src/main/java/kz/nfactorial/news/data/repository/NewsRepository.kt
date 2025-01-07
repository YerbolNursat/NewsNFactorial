package kz.nfactorial.news.data.repository

import androidx.compose.ui.text.toLowerCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kz.nfactorial.news.data.api.NewsApi
import kz.nfactorial.news.data.entity.ItemDTO
import kz.nfactorial.news.data.entity.NewsRowDTO
import kz.nfactorial.news.data.entity.NewsType
import kz.nfactorial.news.data.params.NewsParams
import kz.nfactorial.news.db.dao.NewsDao
import kz.nfactorial.news.db.entity.NewsColumnRoomDTO
import kz.nfactorial.news.db.entity.NewsRowRoomDTO
import java.util.Locale

class NewsRepository(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getNews(type: NewsType): Flow<NewsRowDTO> {
        return flow {
            val list = when (type) {
                NewsType.ROW -> newsDao.getRowNews().map {
                    ItemDTO(
                        id = it.id,
                        image = it.image,
                        title = it.title,
                        subTitle = it.subTitle
                    )
                }

                NewsType.COLUMN -> newsDao.getColumnNews().map {
                    ItemDTO(
                        id = it.id,
                        image = it.image,
                        title = it.title,
                        category = it.category,
                        author = it.author,
                        readTime = it.readTime
                    )
                }
            }
            emit(NewsRowDTO(list))
        }
            .flatMapConcat { roomDTO ->
                flow {
                    emit(roomDTO)
                    val response = newsApi.getNews(type.name.toLowerCase(Locale.getDefault()))
                    response.onSuccess { newsRowDTO ->
                        when (type) {
                            NewsType.ROW -> {
                                newsDao.clearRowNews()
                                newsRowDTO.itemDTOS.map {
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
                                newsRowDTO.itemDTOS.map {
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
                        emit(newsRowDTO)
                    }
                }
            }
    }


    suspend fun getNewsById(
        type: String,
        id: Int
    ) = newsApi.getNewsById(
        type = type,
        id = id
    )

    suspend fun addNews(params: NewsParams) = newsApi.addNews(params)

}
