package dev.shevikina.surfspringschool.domain.db.use_case

import dev.shevikina.surfspringschool.domain.db.BookDBRepository
import dev.shevikina.surfspringschool.domain.db.mapper.toBookModel
import dev.shevikina.surfspringschool.domain.models.BookModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFavoriteBooksUseCase(private val repository: BookDBRepository) {
    operator fun invoke(): Flow<List<BookModel>> =
        repository.getFavoriteBooksStream().map { it.map { book -> book.toBookModel() } }
}