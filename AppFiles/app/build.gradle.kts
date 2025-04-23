plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.gms.google.services)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("dagger.hilt.android.plugin")
}

val bundleId = "com.student.mentalpotion"

android {
    namespace = bundleId
    compileSdk = 35

    defaultConfig {
        applicationId = bundleId
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    // From https://kotest.io/docs/framework/project-setup.html
    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }

}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.firebase.auth)
    debugImplementation(libs.bundles.compose.debug)

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.10.1")

    coreLibraryDesugaring(libs.desugar.jdk.libs)

    implementation(libs.bundles.koin)

    implementation(libs.bundles.ktor)

    testImplementation(libs.junit)

    implementation("io.coil-kt:coil-compose:2.4.0")

    // Arrow
    implementation("io.arrow-kt:arrow-core:2.0.1")
    implementation("io.arrow-kt:arrow-fx-coroutines:2.0.1")

    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // Kotest
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
    testImplementation("io.kotest:kotest-assertions-core:5.8.0")
    testImplementation("io.kotest.extensions:kotest-assertions-arrow:1.3.3")

    // MockK
    testImplementation("io.mockk:mockk:1.13.9")

    // Coroutines test
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.1")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}