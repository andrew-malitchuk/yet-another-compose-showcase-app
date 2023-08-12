package dev.yacsa.model.mapper

import dev.yacsa.domain.model.update.CheckUpdateDomainModel
import dev.yacsa.model.model.update.CheckUpdateUiModel
import org.mapstruct.Mapper

@Mapper
interface CheckUpdateUiDomainMapper {

    fun toDomain(
        checkUpdateUiModel: CheckUpdateUiModel,
    ): CheckUpdateDomainModel

    fun toUi(
        checkUpdateDomainModel: CheckUpdateDomainModel,
    ): CheckUpdateUiModel
}
