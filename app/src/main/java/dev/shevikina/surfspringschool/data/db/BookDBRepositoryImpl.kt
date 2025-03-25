package dev.shevikina.surfspringschool.data.db

import dev.shevikina.surfspringschool.data.db.dao.BookDao
import dev.shevikina.surfspringschool.domain.db.BookDBRepository
import dev.shevikina.surfspringschool.domain.db.model.BookEntity
import kotlinx.coroutines.flow.Flow

class BookDBRepositoryImpl(private val dao: BookDao) : BookDBRepository {
    override fun getFavoriteBooksStream(): Flow<List<BookEntity>> =
        dao.getAllBooks()

    override fun getBookStream(networkId: String): Flow<BookEntity?> =
        dao.getBook(networkId)

    override suspend fun insertFavoriteBook(book: BookEntity) =
        dao.insert(book)

    override suspend fun deleteFavoriteBook(book: BookEntity) =
        dao.delete(book)
}