package dev.shevikina.surfspringschool.data.network.book

import dev.shevikina.surfspringschool.data.network.utils.BaseRemoteDataSource
import dev.shevikina.surfspringschool.data.network.utils.OperationResult
import javax.inject.Inject

class BookRemoteDataSource @Inject constructor(
    private val bookApi: BookApi
) : BaseRemoteDataSource() {
    suspend fun getBooksByTitle(title: String = ""): OperationResult<BookResponseList> =
        safeApiCall { bookApi.getBooksByTitle(query = title) }
}