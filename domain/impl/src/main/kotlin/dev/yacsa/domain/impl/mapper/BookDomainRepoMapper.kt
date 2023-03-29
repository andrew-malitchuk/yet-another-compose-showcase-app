package dev.yacsa.domain.impl.mapper

import dev.yacsa.domain.impl.mapper.base.DomainRepoMapper
import dev.yacsa.domain.model.BookDomainModel
import dev.yacsa.domain.model.StartUpConfigureDomainModel
import dev.yacsa.repository.model.BookRepoModel
import dev.yacsa.repository.model.StartUpConfigureRepoModel
import javax.inject.Inject

class BookDomainRepoMapper @Inject constructor() :
    DomainRepoMapper<BookDomainModel, BookRepoModel>() {

    override fun toDomain(value: BookRepoModel): BookDomainModel {
        return BookDomainModel(
            value.id,
            value.title,
            value.subjects,
            value.copyright,
            value.mediaType,
            value.downloadCount
        )
    }

    override fun toRepo(value: BookDomainModel): BookRepoModel {
        return BookRepoModel(
            value.id,
            value.title,
            value.subjects,
            value.copyright,
            value.mediaType,
            value.downloadCount
        )
    }

}