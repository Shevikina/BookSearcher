package dev.shevikina.surfspringschool.domain.network.repiository

import dev.shevikina.surfspringschool.data.network.book.BookRemoteDataSource
import dev.shevikina.surfspringschool.data.network.utils.OperationResult
import dev.shevikina.surfspringschool.domain.network.mapper.BookResponseToBookModelMapper
import dev.shevikina.surfspringschool.domain.models.BookModel
import dev.shevikina.surfspringschool.domain.network.utils.flatMapIfSuccess
import dev.shevikina.surfspringschool.domain.network.utils.toSuccessResult
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val remoteDataSource: BookRemoteDataSource,
    private val bookResponseToBookModelMapper: BookResponseToBookModelMapper
) {
    suspend fun getBooksByTitle(title: String): OperationResult<List<BookModel>> =
        remoteDataSource.getBooksByTitle(title).flatMapIfSuccess { bookResponseList ->
            bookResponseList.items?.map { bookResponse ->
                bookResponseToBookModelMapper(bookResponse)
            }?.toSuccessResult() ?: OperationResult.Error("Book list is empty")
        }
}