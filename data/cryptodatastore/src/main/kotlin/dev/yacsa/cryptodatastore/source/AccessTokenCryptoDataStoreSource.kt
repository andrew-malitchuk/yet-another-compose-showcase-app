package dev.yacsa.cryptodatastore.source

import dev.yacsa.cryptodatastore.model.AccessTokenCryptoDataStoreModel
import dev.yacsa.cryptodatastore.source.base.BaseCryptoDataStoreSource

abstract class AccessTokenCryptoDataStoreSource :
    BaseCryptoDataStoreSource<AccessTokenCryptoDataStoreModel>() {
    abstract fun collectSomeData()
}
