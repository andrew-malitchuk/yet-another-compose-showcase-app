package dev.yacsa.cryptodatastore.model

import dev.yacsa.cryptodatastore.model.base.BaseCryptoDataStoreModel
import kotlinx.serialization.Serializable

@Serializable
data class AccessTokenCryptoDataStoreModel(
    val accessToken: String?=null
) : BaseCryptoDataStoreModel