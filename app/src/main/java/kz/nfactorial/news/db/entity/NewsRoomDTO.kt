package kz.nfactorial.news.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsColumnRoomDTO(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int = 0,

    @ColumnInfo("image")
    val image: String,

    @ColumnInfo("title")
    val title: String,

    @ColumnInfo("category")
    val category: String,

    @ColumnInfo("author")
    val author: String,

    @ColumnInfo("readTime")
    val readTime: String,

)
@Entity
data class NewsRowRoomDTO(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int = 0,

    @ColumnInfo("image")
    val image: String,

    @ColumnInfo("title")
    val title: String,

    @ColumnInfo("subTitle")
    val subTitle: String

)