package dev.shevikina.surfspringschool.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.shevikina.surfspringschool.data.db.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Insert
    suspend fun insert(book: BookEntity)

    @Query("DELETE FROM books WHERE networkId = :networkId")
    fun deleteBook(networkId: String)

    @Query("SELECT * from books WHERE networkId = :networkId")
    fun getBook(networkId: String): Flow<BookEntity?>

    @Query("SELECT * from books")
    fun getAllBooks(): Flow<List<BookEntity>>
}