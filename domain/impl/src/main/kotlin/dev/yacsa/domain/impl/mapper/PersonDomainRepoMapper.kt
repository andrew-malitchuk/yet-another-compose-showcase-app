package dev.yacsa.domain.impl.mapper

import dev.yacsa.domain.impl.mapper.base.DomainRepoMapper
import dev.yacsa.domain.model.PersonDomainModel
import dev.yacsa.repository.model.PersonRepoModel
import javax.inject.Inject

class PersonDomainRepoMapper @Inject constructor() :
    DomainRepoMapper<PersonDomainModel, PersonRepoModel>() {

    override fun toRepo(value: PersonDomainModel): PersonRepoModel {
        return PersonRepoModel(
            value.id,
            value.birthYear,
            value.deathYear,
            value.name
        )
    }

    override fun toDomain(value: PersonRepoModel): PersonDomainModel {
        return PersonDomainModel(
            birthYear = value.birthYear,
            deathYear = value.deathYear,
            name = value.name
        )
    }

}