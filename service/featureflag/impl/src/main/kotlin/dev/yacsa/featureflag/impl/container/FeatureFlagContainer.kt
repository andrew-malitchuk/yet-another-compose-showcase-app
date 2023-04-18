package dev.yacsa.featureflag.impl.container

import dev.yacsa.books.featureflag.booksFeatureFlagsList
import dev.yacsa.featureflag.FeatureFlagModel
import dev.yacsa.repository.FeatureFlagRepository
import javax.inject.Inject

class FeatureFlagContainer @Inject constructor() {

    @Inject
    lateinit var featureFlagRepository: FeatureFlagRepository

    private val featureFlagList = arrayListOf<FeatureFlagModel>().also {
        it.addAll(booksFeatureFlagsList)
    }

    suspend fun sync() {
        featureFlagList.forEach {
            featureFlagRepository.updateKey(it.key)
        }
    }

}