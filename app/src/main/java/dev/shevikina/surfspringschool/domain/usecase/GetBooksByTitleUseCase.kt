package dev.shevikina.surfspringschool.domain.usecase

import dev.shevikina.surfspringschool.data.utils.OperationResult
import dev.shevikina.surfspringschool.domain.models.BookModel
import dev.shevikina.surfspringschool.domain.repiository.BookRepository
import javax.inject.Inject

class GetBooksByTitleUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    suspend operator fun invoke(title: String): OperationResult<List<BookModel>> =
        bookRepository.getBooksByTitle(title)
}