package dev.shevikina.surfspringschool.domain.db

import dev.shevikina.surfspringschool.data.db.BookEntity
import kotlinx.coroutines.flow.Flow

interface BookDBRepository {

    fun getFavoriteBooksStream(): Flow<List<BookEntity>>

    fun getBookStream(networkId: String): Flow<BookEntity?>

    suspend fun insertFavoriteBook(book: BookEntity)

    suspend fun deleteFavoriteBook(networkId: String)
}