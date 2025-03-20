package dev.shevikina.surfspringschool.data.book

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi {
    @GET(BookUrl.VOLUMES)
    suspend fun getBooksByTitle(
        @Query("q") query: String,
        @Query("maxResults") maxResults: Int = 10
    ): Response<BookResponseList>
}