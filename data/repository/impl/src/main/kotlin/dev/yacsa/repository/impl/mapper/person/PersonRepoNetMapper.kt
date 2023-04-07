package dev.yacsa.repository.impl.mapper.person

import dev.yacsa.network.model.PersonNetModel
import dev.yacsa.repository.impl.mapper.base.RepoDbMapper
import dev.yacsa.repository.impl.mapper.base.RepoNetMapper
import dev.yacsa.repository.model.PersonRepoModel
import javax.inject.Inject

class PersonRepoNetMapper @Inject constructor() :
    RepoNetMapper<PersonRepoModel, PersonNetModel>() {

    override fun toRepo(value: PersonNetModel): PersonRepoModel {
        return PersonRepoModel(
            null,
            value.birthYear,
            value.deathYear,
            value.name
        )
    }

    override fun toNet(value: PersonRepoModel): PersonNetModel {
        return PersonNetModel(
            value.birthYear,
            value.deathYear,
            value.name
        )
    }

}