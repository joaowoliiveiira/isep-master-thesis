import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm()

    val iosTargets = listOf(
        iosX64(), iosArm64(), iosSimulatorArm64()
    )

    iosTargets.forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // ✅ Kotlin Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

                // ✅ Kotlinx Serialization
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

                // ✅ Kotlinx Datetime (optional, but useful)
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")

                // ✅ Arrow Either
                implementation("io.arrow-kt:arrow-core:1.2.0")
            }
        }

        val androidMain by getting

        val iosMain by creating {
            dependsOn(commonMain)
            iosTargets.forEach {
                it.compilations["main"].defaultSourceSet.dependsOn(this)
            }
        }

        val jvmMain by getting
    }
}

android {
    namespace = "com.student.mpbackoffice.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
