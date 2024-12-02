package kz.nfactorial.news.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsDb(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int,

    @ColumnInfo("imageSrc")
    val imageSrc: Int,

    @ColumnInfo("title")
    val title: String,

    @ColumnInfo("subTitle")
    val subTitle: String

)