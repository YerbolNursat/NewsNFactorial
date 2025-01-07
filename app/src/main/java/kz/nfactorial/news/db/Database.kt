package kz.nfactorial.news.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kz.nfactorial.news.db.dao.NewsDao
import kz.nfactorial.news.db.entity.NewsColumnRoomDTO
import kz.nfactorial.news.db.entity.NewsRowRoomDTO

@Database(entities = [NewsRowRoomDTO::class, NewsColumnRoomDTO::class], version = 2)
abstract class NewsDatabase: RoomDatabase() {

    abstract fun getAccountDao(): NewsDao
}

object DatabaseHolder {

    private var _database: NewsDatabase? = null

    val database: NewsDatabase get() = _database!!

    fun getOrCreate(context: Context): NewsDatabase {
        if (_database == null) {
            _database = Room.databaseBuilder(
                context,
                NewsDatabase::class.java,
                "news-database",
            )
                .allowMainThreadQueries()
                .build()
        }

        return database
    }
}