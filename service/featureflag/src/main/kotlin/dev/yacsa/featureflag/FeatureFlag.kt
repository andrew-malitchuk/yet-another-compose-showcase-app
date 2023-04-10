package dev.yacsa.featureflag

abstract class FeatureFlag{
    /**
     * Global switch for feature
     */
    abstract fun isFeatureEnabled():Boolean
}