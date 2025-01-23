package kz.nfactorial.news.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kz.nfactorial.news.data.db.dao.NewsDao
import kz.nfactorial.news.data.db.entity.NewsColumnRoomDTO
import kz.nfactorial.news.data.db.entity.NewsRowRoomDTO
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    single { getNewsDb(androidContext()) }
}


@Database(entities = [NewsRowRoomDTO::class, NewsColumnRoomDTO::class], version = 2)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun getAccountDao(): NewsDao

}


private fun getNewsDb(context: Context) = Room.databaseBuilder(
    context,
    NewsDatabase::class.java,
    "news-database",
)
    .allowMainThreadQueries()
    .build()