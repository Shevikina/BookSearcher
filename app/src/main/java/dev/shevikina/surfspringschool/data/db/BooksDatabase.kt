package dev.shevikina.surfspringschool.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.shevikina.surfspringschool.data.db.dao.BookDao

@Database(
    entities = [BookEntity::class],
    version = 2,
    exportSchema = false
)
abstract class BooksDatabase : RoomDatabase() {
    abstract val bookDao: BookDao

    companion object {
        const val DB_NAME = "favorite_books"
    }
}