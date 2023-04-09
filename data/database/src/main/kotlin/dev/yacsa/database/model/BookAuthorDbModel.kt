package dev.yacsa.database.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import dev.yacsa.database.model.base.BaseDbModel
import dev.yacsa.database.model.relationships.BookAuthorRelationship

data class BookAuthorDbModel(

    @Embedded
    var book: BookDbModel,

    @Relation(
        parentColumn = "bookId",
        entity = PersonDbModel::class,
        entityColumn = "personId",
        associateBy = Junction(
            value = BookAuthorRelationship::class,
            parentColumn = "bookId",
            entityColumn = "personId",
        ),
    )
    var author: List<PersonDbModel>,

) : BaseDbModel
