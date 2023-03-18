package dev.yacsa.datastore.model

import dev.yacsa.datastore.model.base.BaseDataStoreModel
import kotlinx.serialization.Serializable

@Serializable
enum class ThemeDateStoreModel : BaseDataStoreModel {
    LIGHT, DARK
}