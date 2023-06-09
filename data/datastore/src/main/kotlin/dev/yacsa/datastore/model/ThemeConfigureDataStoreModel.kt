package dev.yacsa.datastore.model

import dev.yacsa.datastore.model.base.BaseDataStoreModel
import kotlinx.serialization.Serializable

@Serializable
data class ThemeConfigureDataStoreModel(
    val theme:ThemeMode
) : BaseDataStoreModel

enum class ThemeMode{
    LIGHT, DARK, AUTO
}