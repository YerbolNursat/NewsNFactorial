package kz.nfactorial.news.data.api

import kz.nfactorial.news.data.entity.NewsItemResponse
import kz.nfactorial.news.data.entity.NewsResponse
import kz.nfactorial.news.domain.entity.params.NewsParams
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApi {

    @GET("news")
    suspend fun getNews(
        @Query("type") type: String
    ): Result<NewsResponse>

    @GET("news/{id}")
    suspend fun getNewsById(
        @Query("type") type: String,
        @Path("id") id: Int
    ): Result<Any>

    @POST("news")
    suspend fun addNews(
       @Body params: NewsParams
    ): Result<NewsItemResponse>

}