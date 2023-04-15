package dev.yacsa.featureflag.impl.container

import dev.yacsa.books.featureflag.fooList
import dev.yacsa.featureflag.FooFlag
import dev.yacsa.repository.FeatureFlagRepository
import javax.inject.Inject

class FeatureFlagContainer @Inject constructor() {

    @Inject
    lateinit var featureFlagRepository: FeatureFlagRepository

    val featureFlagList = arrayListOf<FooFlag>().also {
        it.addAll(fooList)
    }

    suspend fun sync() {
        featureFlagList.forEach {
            featureFlagRepository.updateKey(it.key)
        }
    }

}