package dev.shevikina.surfspringschool.data.network.book.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.shevikina.surfspringschool.data.network.book.BookApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object BookApiModule {

    @Singleton
    @Provides
    fun providesBookApi(retrofit: Retrofit): BookApi = retrofit.create(BookApi::class.java)
}