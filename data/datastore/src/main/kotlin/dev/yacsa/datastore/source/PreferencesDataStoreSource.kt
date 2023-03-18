package dev.yacsa.datastore.source

import dev.yacsa.datastore.model.PreferencesDataStoreModel
import dev.yacsa.datastore.source.base.BaseDataStoreSource

abstract class PreferencesDataStoreSource : BaseDataStoreSource<PreferencesDataStoreModel>() {
    abstract fun collectSomeData()
}