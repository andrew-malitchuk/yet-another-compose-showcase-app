package dev.yacsa.datastore.model

import dev.yacsa.datastore.model.base.BaseDataStoreModel
import kotlinx.serialization.Serializable

@Serializable
data class PreferencesDataStoreModel(
    val theme: ThemeDateStoreModel = ThemeDateStoreModel.LIGHT
) : BaseDataStoreModel