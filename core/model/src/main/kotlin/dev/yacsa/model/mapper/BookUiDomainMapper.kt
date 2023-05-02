package dev.yacsa.model.mapper

import dev.yacsa.domain.model.BookDomainModel
import dev.yacsa.model.mapper.base.UiDomainMapper
import dev.yacsa.model.model.BookUiModel
import javax.inject.Inject

class BookUiDomainMapper @Inject constructor(
    private val formatsUiDomainMapper: FormatsUiDomainMapper,
    private val personUiDomainMapper: PersonUiDomainMapper,
) : UiDomainMapper<BookUiModel, BookDomainModel>() {

    override fun toUi(value: BookDomainModel): BookUiModel {
        return BookUiModel(
            value.id,
            value.title,
            value.subjects,
            value.authors?.filterNotNull()?.map(personUiDomainMapper::toUi),
            value.translators?.filterNotNull()?.map(personUiDomainMapper::toUi),
            value.bookshelves,
            value.languages,
            value.copyright,
            value.mediaType,
            value.formats?.let { formatsUiDomainMapper.toUi(it) },
            value.downloadCount,
        ).also {
            it.isCached=value.isCached
        }
    }

    override fun toDomain(value: BookUiModel): BookDomainModel {
        return BookDomainModel(
            value.id,
            value.title,
            value.subjects,
            value.authors?.filterNotNull()?.map(personUiDomainMapper::toDomain),
            value.translators?.filterNotNull()?.map(personUiDomainMapper::toDomain),
            value.bookshelves,
            value.languages,
            value.copyright,
            value.mediaType,
            value.formats?.let { formatsUiDomainMapper.toDomain(it) },
            value.downloadCount,
        )
    }
}
