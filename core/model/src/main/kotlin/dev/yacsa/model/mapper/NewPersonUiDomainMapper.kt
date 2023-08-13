package dev.yacsa.model.mapper

import dev.yacsa.domain.model.PersonDomainModel
import dev.yacsa.model.model.PersonUiModel
import org.mapstruct.Mapper

@Mapper
interface NewPersonUiDomainMapper {

    fun toDomain(
        personUiModel: PersonUiModel,
    ): PersonDomainModel

    fun toUi(
        personDomainModel: PersonDomainModel,
    ): PersonUiModel
}
