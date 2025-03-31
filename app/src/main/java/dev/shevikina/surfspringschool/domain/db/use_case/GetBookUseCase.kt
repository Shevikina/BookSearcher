package dev.shevikina.surfspringschool.domain.db.use_case

import dev.shevikina.surfspringschool.domain.db.BookDBRepository
import dev.shevikina.surfspringschool.domain.db.mapper.toBookModel
import dev.shevikina.surfspringschool.domain.models.BookModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class GetBookUseCase(private val repository: BookDBRepository) {
    suspend operator fun invoke(id: String): BookModel? =
        repository.getBookStream(id).map { it?.toBookModel() }.first()
}