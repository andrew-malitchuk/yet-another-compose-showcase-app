import com.android.build.gradle.LibraryExtension
import dev.yacsa.convention.VersionControl.TARGET_SDK
import dev.yacsa.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.devtools.ksp")
                apply("org.jmailen.kotlinter")
            }

            with(extensions) {
                configure<LibraryExtension> {
                    configureKotlinAndroid(this)
                    defaultConfig.targetSdk = TARGET_SDK
                }
            }
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
//                add("implementation",libs.findLibrary("hilt.android").get())
                add("implementation","com.squareup.logcat:logcat:0.1")
            }
        }
    }
}