package dev.yacsa.network.impl.service

import dev.yacsa.network.model.BookNetModel
import dev.yacsa.network.model.ResultNetModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksApiService {

    @GET("/books/{id}")
    suspend fun getBook(
        @Path("id") id: Int,
    ): BookNetModel?

    @GET("/books")
    suspend fun getBooks(
        @Query("page") page: Int,
    ): ResultNetModel?

    @GET("/books")
    suspend fun search(
        @Query("search") search: String
    ): ResultNetModel?

}
