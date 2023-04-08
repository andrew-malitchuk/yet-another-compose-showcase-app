import com.android.build.api.dsl.ApplicationExtension
import dev.yacsa.convention.VersionControl.APPLICATION_ID
import dev.yacsa.convention.VersionControl.TARGET_SDK
import dev.yacsa.convention.VersionControl.VERSION_CODE
import dev.yacsa.convention.VersionControl.VERSION_NAME
import dev.yacsa.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jmailen.kotlinter")
            }
            extensions.configure<ApplicationExtension> {
                defaultConfig.apply {
                    applicationId = APPLICATION_ID
                    targetSdk = TARGET_SDK

                    versionCode=VERSION_CODE
                    versionName= VERSION_NAME
                }
                configureKotlinAndroid(this)
            }
        }
    }
}