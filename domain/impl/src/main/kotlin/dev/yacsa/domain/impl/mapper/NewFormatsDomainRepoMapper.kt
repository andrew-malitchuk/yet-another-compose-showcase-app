package dev.yacsa.domain.impl.mapper

import dev.yacsa.domain.model.FormatsDomainModel
import dev.yacsa.repository.model.FormatsRepoModel
import org.mapstruct.Mapper

@Mapper
interface NewFormatsDomainRepoMapper {

    fun toRepo(
        formatsDomainModel: FormatsDomainModel
    ): FormatsRepoModel

    fun toDomain(
        formatsRepoModel: FormatsRepoModel
    ): FormatsDomainModel

}