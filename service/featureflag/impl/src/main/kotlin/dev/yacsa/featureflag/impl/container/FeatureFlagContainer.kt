package dev.yacsa.featureflag.impl.container

import dev.yacsa.books.featureflag.booksFeatureFlags
import dev.yacsa.featureflag.BaseFeatureFlagModel
import dev.yacsa.repository.FeatureFlagRepository
import javax.inject.Inject

class FeatureFlagContainer @Inject constructor(
    private val featureFlagRepository: FeatureFlagRepository
) {

    val featureFlagList = arrayListOf<BaseFeatureFlagModel>().also {
        it.addAll(booksFeatureFlags)
    }

    suspend fun sync() {

    }


}