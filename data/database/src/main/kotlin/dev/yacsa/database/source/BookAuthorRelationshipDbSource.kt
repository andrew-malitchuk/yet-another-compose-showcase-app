package dev.yacsa.database.source

import dev.yacsa.database.model.BookAuthorDbModel
import dev.yacsa.database.model.relationships.BookAuthorRelationship
import kotlinx.coroutines.flow.Flow

interface BookAuthorRelationshipDbSource {
    suspend fun get(): List<BookAuthorDbModel>
    suspend fun getFlow(): Flow<List<BookAuthorDbModel>>
    suspend fun insert(value: BookAuthorRelationship)
}
