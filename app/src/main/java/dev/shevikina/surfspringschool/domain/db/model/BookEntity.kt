package dev.shevikina.surfspringschool.domain.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_books")
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val networkId: String,
    val author: String,
    val title: String,
    val imageUrl: String,
    val description: String,
    val publishedDate: String
)
