package dev.yacsa.repository.impl.mapper.book

import dev.yacsa.database.model.BookDbModel
import dev.yacsa.repository.impl.mapper.FormatsRepoDbMapper
import dev.yacsa.repository.impl.mapper.base.RepoDbMapper
import dev.yacsa.repository.impl.mapper.person.PersonRepoDbMapper
import dev.yacsa.repository.model.BookRepoModel
import dev.yacsa.repository.model.FormatsRepoModel
import javax.inject.Inject

class BookRepoDbMapper @Inject constructor(
    private val formatsRepoDbMapper: FormatsRepoDbMapper,
    private val personRepoDbMapper: PersonRepoDbMapper
) : RepoDbMapper<BookRepoModel, BookDbModel>() {

    // TODO: fix
    override fun toRepo(value: BookDbModel): BookRepoModel {
        return BookRepoModel(
            value.id,
            value.title,
            null,
            null,
            value.copyright,
            value.mediaType,
            formatsRepoDbMapper.toRepo(value.formats),
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
            formatsRepoDbMapper.toDb(value.formats?: FormatsRepoModel()),
            value.downloadCount
        )
    }
}