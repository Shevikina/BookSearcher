package dev.shevikina.surfspringschool.domain.repiository

import dev.shevikina.surfspringschool.data.book.BookRemoteDataSource
import dev.shevikina.surfspringschool.data.utils.OperationResult
import dev.shevikina.surfspringschool.domain.mappers.BookResponseToBookModelMapper
import dev.shevikina.surfspringschool.domain.models.BookModel
import dev.shevikina.surfspringschool.domain.utils.flatMapIfSuccess
import dev.shevikina.surfspringschool.domain.utils.toSuccessResult
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val remoteDataSource: BookRemoteDataSource,
    private val bookResponseToBookModelMapper: BookResponseToBookModelMapper
) {
    suspend fun getBooksByTitle(title: String): OperationResult<List<BookModel>> =
        remoteDataSource.getBooksByTitle(title).flatMapIfSuccess { bookResponseList ->
            bookResponseList.map { bookResponse ->
                bookResponseToBookModelMapper(bookResponse)
            }.toSuccessResult()
        }
}