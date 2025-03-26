package dev.shevikina.surfspringschool.domain.db.use_case

import dev.shevikina.surfspringschool.domain.db.BookDBRepository
import dev.shevikina.surfspringschool.domain.db.mapper.toBookEntity
import dev.shevikina.surfspringschool.domain.models.BookModel
import dev.shevikina.surfspringschool.domain.models.InvalidBookModelException

class AddBookUseCase(private val repository: BookDBRepository) {
    @Throws(InvalidBookModelException::class)
    suspend operator fun invoke(book: BookModel) =
        repository.insertFavoriteBook(
            book.toBookEntity() ?: throw InvalidBookModelException("Ошибка аутентификации книги")
        )
}