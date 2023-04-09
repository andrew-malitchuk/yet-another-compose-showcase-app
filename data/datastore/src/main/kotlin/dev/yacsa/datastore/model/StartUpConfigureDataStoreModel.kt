package dev.yacsa.datastore.model

import dev.yacsa.datastore.model.base.BaseDataStoreModel
import kotlinx.serialization.Serializable

@Serializable
data class StartUpConfigureDataStoreModel(
    val hasBeenOnboardingShown: Boolean = false,
) : BaseDataStoreModel
