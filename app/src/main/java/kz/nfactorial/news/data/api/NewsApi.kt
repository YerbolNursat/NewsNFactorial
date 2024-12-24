package kz.nfactorial.news.data.api

import kz.nfactorial.news.data.entity.ItemDTO
import kz.nfactorial.news.data.entity.NewsRowDTO
import kz.nfactorial.news.data.params.NewsParams
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApi {

    @GET("news")
    suspend fun getNews(
        @Query("type") type: String
    ): Result<NewsRowDTO>

    @GET("news/{id}")
    suspend fun getNewsById(
        @Query("type") type: String,
        @Path("id") id: Int
    ): Result<Any>

    @POST("news")
    suspend fun addNews(
       @Body params: NewsParams
    ): Result<ItemDTO>

}