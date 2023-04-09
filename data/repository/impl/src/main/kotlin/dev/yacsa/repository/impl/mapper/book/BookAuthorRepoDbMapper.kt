package dev.yacsa.repository.impl.mapper.book

import dev.yacsa.database.model.BookAuthorDbModel
import dev.yacsa.database.model.BookDbModel
import dev.yacsa.repository.impl.mapper.FormatsRepoDbMapper
import dev.yacsa.repository.impl.mapper.base.RepoDbMapper
import dev.yacsa.repository.impl.mapper.person.PersonRepoDbMapper
import dev.yacsa.repository.model.BookRepoModel
import dev.yacsa.repository.model.FormatsRepoModel
import javax.inject.Inject

class BookAuthorRepoDbMapper @Inject constructor(
    private val formatsRepoDbMapper: FormatsRepoDbMapper,
    private val personRepoDbMapper: PersonRepoDbMapper,
) : RepoDbMapper<BookRepoModel, BookAuthorDbModel>() {

    // TODO: fix
    override fun toRepo(value: BookAuthorDbModel): BookRepoModel {
        return BookRepoModel(
            value.book.id,
            value.book.title,
            null,
            value.author.filterNotNull().map(personRepoDbMapper::toRepo),
            value.book.copyright,
            value.book.mediaType,
            formatsRepoDbMapper.toRepo(value.book.formats),
            value.book.downloadCount,
        )
    }

    // TODO: fix
    override fun toDb(value: BookRepoModel): BookAuthorDbModel {
        return BookAuthorDbModel(
            book = BookDbModel(
                value.id ?: 0,
                value.title,
                value.copyright,
                value.mediaType,
                formatsRepoDbMapper.toDb(value.formats ?: FormatsRepoModel()),
                value.downloadCount,
            ),
            author = value.authors?.filterNotNull()?.map(personRepoDbMapper::toDb) ?: emptyList(),
        )
    }
}
