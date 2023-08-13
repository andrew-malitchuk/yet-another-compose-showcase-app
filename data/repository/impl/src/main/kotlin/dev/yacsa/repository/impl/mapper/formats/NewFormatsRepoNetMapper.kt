package dev.yacsa.repository.impl.mapper.formats

import dev.yacsa.network.model.FormatsNetModel
import dev.yacsa.repository.model.FormatsRepoModel
import org.mapstruct.Mapper

@Mapper
interface NewFormatsRepoNetMapper {

    fun toRepo(
        formatsNetModel: FormatsNetModel,
    ): FormatsRepoModel

    fun toNet(
        formatsRepoModel: FormatsRepoModel,
    ): FormatsNetModel
}
