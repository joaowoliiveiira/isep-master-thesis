import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm("desktop")

    room {
        schemaDirectory("$projectDir/schemas")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)

                implementation(libs.androidx.lifecycle.viewmodel)
                implementation(libs.androidx.lifecycle.runtime.compose)
                implementation(libs.androidx.room.runtime)
                implementation(libs.sqlite.bundled)

                implementation(libs.koin.compose)
                implementation(libs.koin.compose.viewmodel)
                api(libs.koin.core)

                implementation(libs.bundles.ktor)
                implementation(libs.bundles.coil)

                implementation(libs.jetbrains.compose.navigation)

                implementation("io.github.jan-tennert.supabase:auth-kt:3.1.4")

                // Firebase Kotlin SDK (KMP-compatible)
                implementation("dev.gitlive:firebase-firestore:1.8.0")
                implementation("dev.gitlive:firebase-auth:1.8.0")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(compose.preview)
                implementation(libs.androidx.activity.compose)

                implementation(libs.koin.android)
                implementation(libs.koin.androidx.compose)
                implementation(libs.ktor.client.okhttp)

                // Android Firebase SDKs (required for Android target)
                implementation("com.google.firebase:firebase-auth-ktx")
                implementation("com.google.firebase:firebase-firestore-ktx")
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.kotlinx.coroutines.swing)
                implementation(libs.ktor.client.okhttp)

                // JVM-specific Firestore/Auth implementation for Desktop
                implementation("dev.gitlive:firebase-firestore-jvm:1.8.0")
                implementation("dev.gitlive:firebase-auth-jvm:1.8.0")
            }
        }
    }
}

android {
    namespace = "com.student.mpbackoffice"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.student.mpbackoffice"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.runtime.android)
    implementation(libs.androidx.ui.text.android)
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "com.student.mpbackoffice.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.student.mpbackoffice"
            packageVersion = "1.0.0"
        }
    }
}
