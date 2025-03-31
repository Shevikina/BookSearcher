package dev.shevikina.surfspringschool.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val networkId: String,
    val author: String,
    val title: String,
    val imageUrl: String,
    val description: String,
    val publishedDate: String
)
