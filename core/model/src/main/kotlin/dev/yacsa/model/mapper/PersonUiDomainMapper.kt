package dev.yacsa.model.mapper

import dev.yacsa.domain.model.PersonDomainModel
import dev.yacsa.model.mapper.base.UiDomainMapper
import dev.yacsa.model.model.PersonUiModel
import javax.inject.Inject


class PersonUiDomainMapper @Inject constructor() :
    UiDomainMapper<PersonUiModel, PersonDomainModel>() {

    override fun toUi(value: PersonDomainModel): PersonUiModel {
        return PersonUiModel(
            value.id,
            value.birthYear,
            value.deathYear,
            value.name
        )
    }

    override fun toDomain(value: PersonUiModel): PersonDomainModel {
        return PersonDomainModel(
            value.id,
            value.birthYear,
            value.deathYear,
            value.name
        )
    }


}