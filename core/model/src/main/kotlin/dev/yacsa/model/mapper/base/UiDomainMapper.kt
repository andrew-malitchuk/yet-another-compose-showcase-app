package dev.yacsa.model.mapper.base

import dev.yacsa.domain.model.base.BaseDomainModel
import dev.yacsa.model.model.base.BaseUiModel

abstract class UiDomainMapper<Ui : BaseUiModel, Domain : BaseDomainModel> {
    abstract fun toUi(value: Domain): Ui
    abstract fun toDomain(value: Ui): Domain
}
