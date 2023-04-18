pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://jitpack.io")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}
rootProject.name = "YACSA"

include(":app")
include(":feature:books")
include(":feature:format")
include(":feature:person")
include(":feature:main")
include(":feature:navigation")
include(":feature:onboarding")
include(":feature:featureflagmanager")
include(":feature:notfound")
include(":core:common")
include(":core:platform")
include(":core:ui")
include(":core:model")
include(":core:localization")
include(":domain")
include(":domain:impl")
include(":data")
include(":data:network")
include(":data:network:impl")
include(":data:datastore")
include(":data:datastore:impl")
include(":data:database")
include(":data:database:impl")
include(":data:cryptodatastore")
include(":data:cryptodatastore:impl")
include(":data:remoteconfig")
include(":data:remoteconfig:impl")
include(":data:repository")
include(":data:repository:impl")
include(":service")
include(":service:analytics")
include(":service:featureflag")
include(":service:featureflag:impl")
