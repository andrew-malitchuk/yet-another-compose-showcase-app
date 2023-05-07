package dev.yacsa.repository.impl.mapper.person

import dev.yacsa.network.model.PersonNetModel
import dev.yacsa.repository.model.PersonRepoModel
import org.mapstruct.Mapper

@Mapper
interface NewPersonRepoNetMapper {

    fun toRepo(
        personNetModel: PersonNetModel
    ): PersonRepoModel

    fun toNet(
        personRepoModel: PersonRepoModel
    ): PersonNetModel

}