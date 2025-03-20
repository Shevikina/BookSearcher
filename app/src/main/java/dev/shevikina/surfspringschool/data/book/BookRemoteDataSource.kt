package dev.shevikina.surfspringschool.data.book

import dev.shevikina.surfspringschool.data.utils.BaseRemoteDataSource
import dev.shevikina.surfspringschool.data.utils.OperationResult
import javax.inject.Inject

class BookRemoteDataSource @Inject constructor(
    private val bookApi: BookApi
) : BaseRemoteDataSource() {
    suspend fun getBooksByTitle(title: String = ""): OperationResult<BookResponseList> =
        safeApiCall { bookApi.getBooksByTitle(query = title) }
}