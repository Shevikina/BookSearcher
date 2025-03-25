package dev.shevikina.surfspringschool.domain.network.usecase

import dev.shevikina.surfspringschool.data.network.utils.OperationResult
import dev.shevikina.surfspringschool.domain.models.BookModel
import dev.shevikina.surfspringschool.domain.network.repiository.BookRepository
import javax.inject.Inject

class GetBooksByTitleUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    suspend operator fun invoke(title: String): OperationResult<List<BookModel>> =
        bookRepository.getBooksByTitle(title)
}