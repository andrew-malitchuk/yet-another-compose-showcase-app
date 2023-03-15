package dev.yacsa.network.impl.service

import dev.yacsa.network.model.ResultNetModel
import retrofit2.http.GET
import retrofit2.http.Path

interface BooksApiService {

    @GET("/books/{page}")
    suspend fun getBooks(
        @Path("page") page: Int,
    ): ResultNetModel?
}