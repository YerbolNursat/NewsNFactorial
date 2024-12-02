package kz.nfactorial.news.db.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kz.nfactorial.news.db.entity.NewsDb

@Dao
interface NewsDao {

    @Query("SELECT * FROM NewsDb LIMIT 1")
    fun getAccount(): NewsDb?

    @Insert
    fun insertAccount(newsDb: NewsDb)

    @Query("DELETE from NewsDb")
    fun deleteAll()

}