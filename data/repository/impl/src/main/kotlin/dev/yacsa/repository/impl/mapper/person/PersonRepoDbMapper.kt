package dev.yacsa.repository.impl.mapper.person

import dev.yacsa.database.model.FormatsDbModel
import dev.yacsa.database.model.PersonDbModel
import dev.yacsa.repository.impl.mapper.base.RepoDbMapper
import dev.yacsa.repository.model.FormatsRepoModel
import dev.yacsa.repository.model.PersonRepoModel
import javax.inject.Inject

class PersonRepoDbMapper @Inject constructor() :
    RepoDbMapper<PersonRepoModel, PersonDbModel>() {

    override fun toRepo(value: PersonDbModel): PersonRepoModel {
        return PersonRepoModel(
            value.id,
            value.birthYear,
            value.deathYear,
            value.name
        )
    }

    override fun toDb(value: PersonRepoModel): PersonDbModel {
        return PersonDbModel(
            birthYear = value.birthYear,
            deathYear = value.deathYear,
            name = value.name
        )
    }

}