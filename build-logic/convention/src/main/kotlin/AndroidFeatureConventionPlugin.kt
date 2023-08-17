import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            pluginManager.apply{
                apply("yacsa.android.library")
                apply("yacsa.android.hilt")
                apply("com.google.devtools.ksp")
                apply("io.gitlab.arturbosch.detekt")
            }
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
//                add("implementation", project(":core:model"))
//                add("implementation", libs.findLibrary("coil.kt").get())
                add("implementation", "io.github.serpro69:kotlin-faker:1.14.0")

            }
        }
    }
}