package dev.yacsa.featureflag

abstract class FeatureFlag{
    /**
     * Global switch for feature
     */
    abstract suspend fun isFeatureEnabled():Boolean
}