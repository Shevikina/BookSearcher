package dev.shevikina.surfspringschool.domain.db.mapper

import dev.shevikina.surfspringschool.data.db.BookEntity
import dev.shevikina.surfspringschool.domain.models.BookModel

fun BookModel.toBookEntity(): BookEntity? {
    return if (id.isEmpty()) null
    else BookEntity(
//      Entity id autoGenerate,
        networkId = id,
        author = author,
        title = title,
        imageUrl = imageUrl,
        description = description,
        publishedDate = publishedDate
    )
}

fun BookEntity.toBookModel(): BookModel =
    BookModel(
        id = networkId,
        author = author,
        title = title,
        imageUrl = imageUrl,
        description = description,
        publishedDate = publishedDate
    )