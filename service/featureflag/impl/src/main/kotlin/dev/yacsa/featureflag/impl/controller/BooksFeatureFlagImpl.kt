package dev.yacsa.featureflag.impl.controller

import dev.yacsa.books.featureflag.BooksFeatureFlag
import dev.yacsa.books.featureflag.BooksFlags
import dev.yacsa.repository.FeatureFlagRepository
import javax.inject.Inject

class BooksFeatureFlagImpl @Inject constructor(
    private val featureFlagRepository: FeatureFlagRepository
) : BooksFeatureFlag() {

    override suspend fun isFoo(): Boolean {
        return featureFlagRepository.getFeatureFlagValue(BooksFlags.FOO.key, BooksFlags.FOO.key).getOrElse {
            false
        }
    }

    override suspend fun isFeatureEnabled(): Boolean {
        return featureFlagRepository.getFeatureFlagValue(BooksFlags.BOOKS.key,BooksFlags.BOOKS.key).getOrElse {
            true
        }
    }

}