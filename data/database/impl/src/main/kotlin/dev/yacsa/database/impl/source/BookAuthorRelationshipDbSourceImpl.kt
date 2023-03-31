package dev.yacsa.database.impl.source

import dev.yacsa.database.impl.dao.BookAuthorRelationshipDao
import dev.yacsa.database.impl.dao.PersonDbDao
import dev.yacsa.database.model.BookAuthorDbModel
import dev.yacsa.database.model.relationships.BookAuthorRelationship
import dev.yacsa.database.source.BookAuthorRelationshipDbSource
import dev.yacsa.database.source.PersonDbSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookAuthorRelationshipDbSourceImpl @Inject constructor(
    private val bookAuthorRelationshipDao: BookAuthorRelationshipDao
): BookAuthorRelationshipDbSource {

    override suspend fun get():List<BookAuthorDbModel> {
        return bookAuthorRelationshipDao.get()
    }

    override suspend fun getFlow(): Flow<List<BookAuthorDbModel>> {
        return bookAuthorRelationshipDao.getFlow()
    }

    override suspend fun insert(value: BookAuthorRelationship) {
        bookAuthorRelationshipDao.insert(value)
    }


}