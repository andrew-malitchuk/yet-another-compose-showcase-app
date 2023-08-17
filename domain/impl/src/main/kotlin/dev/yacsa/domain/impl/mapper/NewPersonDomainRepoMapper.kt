package dev.yacsa.domain.impl.mapper

import dev.yacsa.domain.model.PersonDomainModel
import dev.yacsa.repository.model.PersonRepoModel
import org.mapstruct.Mapper

@Mapper
interface NewPersonDomainRepoMapper {

    fun toRepo(
        personDomainModel: PersonDomainModel,
    ): PersonRepoModel

    fun toDomain(
        personRepoModel: PersonRepoModel,
    ): PersonDomainModel
}
