package dev.yacsa.repository.impl.mapper

import dev.yacsa.database.model.BookDbModel
import dev.yacsa.network.model.BookNetModel
import dev.yacsa.repository.impl.mapper.base.RepoDbMapper
import dev.yacsa.repository.impl.mapper.base.RepoNetMapper
import dev.yacsa.repository.model.BookRepoModel
import javax.inject.Inject

class BookRepoDbMapper @Inject constructor() :
    RepoDbMapper<BookRepoModel, BookDbModel>() {
    // TODO: fix
    override fun toRepo(value: BookDbModel): BookRepoModel {
        return BookRepoModel(
            value.id,
            value.title,
            null,
            value.copyright,
            value.mediaType,
            value.downloadCount
        )
    }

    // TODO: fix
    override fun toDb(value: BookRepoModel): BookDbModel {
        return BookDbModel(
            value.id ?: 0,
            value.title,
            value.copyright,
            value.mediaType,
            "",
            value.downloadCount
        )
    }
}