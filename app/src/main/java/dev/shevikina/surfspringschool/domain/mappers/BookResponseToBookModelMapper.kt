package dev.shevikina.surfspringschool.domain.mappers

import dev.shevikina.surfspringschool.data.book.BookResponse
import dev.shevikina.surfspringschool.domain.models.BookModel
import javax.inject.Inject

class BookResponseToBookModelMapper @Inject constructor() : (BookResponse) -> BookModel {
    override operator fun invoke(from: BookResponse): BookModel =
        BookModel(
            id = from.volumeInfo?.id ?: "",
            author = from.volumeInfo?.authors?.getOrNull(0) ?: "Unknown",
            title = from.volumeInfo?.title ?: "Untitled",
            imageUrl = from.volumeInfo?.imageLinks?.thumbnail ?: "",
            description = from.volumeInfo?.description ?: ""
        )
}