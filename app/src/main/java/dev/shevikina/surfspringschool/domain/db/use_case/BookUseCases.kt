package dev.shevikina.surfspringschool.domain.db.use_case

class BookUseCases(
    val getBookUseCase: GetBookUseCase,
    val getBooksUseCase: GetFavoriteBooksUseCase,
    val addBookUseCase: AddBookUseCase,
    val deleteBookUseCase: DeleteBookUseCase
)