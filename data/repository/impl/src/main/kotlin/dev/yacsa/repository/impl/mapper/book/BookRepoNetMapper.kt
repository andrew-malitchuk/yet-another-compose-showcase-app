package dev.yacsa.repository.impl.mapper.book

import dev.yacsa.network.model.BookNetModel
import dev.yacsa.repository.impl.mapper.FormatsRepoNetMapper
import dev.yacsa.repository.impl.mapper.base.RepoNetMapper
import dev.yacsa.repository.impl.mapper.person.PersonRepoNetMapper
import dev.yacsa.repository.model.BookRepoModel
import dev.yacsa.repository.model.FormatsRepoModel
import dev.yacsa.repository.model.PersonRepoModel
import javax.inject.Inject

class BookRepoNetMapper @Inject constructor(
    private val formatsRepoNetMapper: FormatsRepoNetMapper,
    private val personRepoNetMapper: PersonRepoNetMapper,
) : RepoNetMapper<BookRepoModel, BookNetModel>() {

    override fun toRepo(value: BookNetModel): BookRepoModel {
        val authorsRepos = ArrayList<PersonRepoModel>()
        value.authors?.let { list ->
            list.forEach { person ->
                person?.let {
                    authorsRepos.add(
                        personRepoNetMapper.toRepo(it),
                    )
                }
            }
        }

        return BookRepoModel(
            value.id,
            value.title,
            value.subjects,
            authorsRepos,
            value.copyright,
            value.mediaType,
            value.formats?.let { formatsRepoNetMapper.toRepo(it) } ?: FormatsRepoModel(),
            value.downloadCount,
        )
    }

    // TODO: fix
    override fun toNet(value: BookRepoModel): BookNetModel {
        return BookNetModel(
            value.id,
            value.title,
            value.subjects,
            null,
            null,
            null,
            value.copyright,
            value.mediaType,
            value.formats?.let { formatsRepoNetMapper.toNet(it) },
            value.downloadCount,
        )
    }
}
