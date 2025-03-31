package dev.shevikina.surfspringschool.data.db.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.shevikina.surfspringschool.data.db.BookDBRepositoryImpl
import dev.shevikina.surfspringschool.data.db.BooksDatabase
import dev.shevikina.surfspringschool.domain.db.BookDBRepository
import dev.shevikina.surfspringschool.domain.db.use_case.AddBookUseCase
import dev.shevikina.surfspringschool.domain.db.use_case.BookUseCases
import dev.shevikina.surfspringschool.domain.db.use_case.DeleteBookUseCase
import dev.shevikina.surfspringschool.domain.db.use_case.GetBookUseCase
import dev.shevikina.surfspringschool.domain.db.use_case.GetFavoriteBooksUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): BooksDatabase =
        Room.databaseBuilder(
            app,
            BooksDatabase::class.java,
            BooksDatabase.DB_NAME
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideNoteRepository(db: BooksDatabase): BookDBRepository =
        BookDBRepositoryImpl(db.bookDao)

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: BookDBRepository): BookUseCases =
        BookUseCases(
            getBookUseCase = GetBookUseCase(repository),
            getBooksUseCase = GetFavoriteBooksUseCase(repository),
            addBookUseCase = AddBookUseCase(repository),
            deleteBookUseCase = DeleteBookUseCase(repository)
        )
}