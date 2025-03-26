package dev.shevikina.surfspringschool.domain.db.use_case

import dev.shevikina.surfspringschool.domain.db.BookDBRepository
import dev.shevikina.surfspringschool.domain.db.mapper.toBookModel
import dev.shevikina.surfspringschool.domain.models.BookModel
import kotlinx.coroutines.flow.singleOrNull

class GetFavoriteBooksUseCase(private val repository: BookDBRepository) {
    suspend operator fun invoke(): List<BookModel>? =
        repository.getFavoriteBooksStream().singleOrNull()?.map { it.toBookModel() }
}