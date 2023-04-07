package dev.yacsa.database.model.relationships

import androidx.room.Entity
import dev.yacsa.database.model.base.BaseDbModel

@Entity(primaryKeys = ["bookId", "personId"])
data class BookAuthorRelationship(
    val bookId: Int,
    val personId: Int,
) : BaseDbModel