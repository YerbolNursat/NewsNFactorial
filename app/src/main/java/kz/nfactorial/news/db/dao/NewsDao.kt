package kz.nfactorial.news.db.dao
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kz.nfactorial.news.db.entity.NewsRoomDTO

@Dao
interface NewsDao {

    @Query("SELECT * FROM NewsRoomDTO")
    suspend fun getNews(): List<NewsRoomDTO>

    @Insert
    suspend fun insertNews(newsRoomDTO: NewsRoomDTO)

    @Query("DELETE FROM NewsRoomDTO")
    suspend fun clearNews()

}