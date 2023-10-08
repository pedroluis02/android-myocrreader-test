@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.github.pedroluis02.myocrreader1"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.github.pedroluis02.myocrreader1"
        minSdk = 23
        targetSdk = 33
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
    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(platform(libs.jetbrains.kotlin.bom))

    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.bundles.androidx.compose)

    implementation(libs.bundles.androidx.camera)

    implementation(libs.google.mlkit.text.recognition)

    //implementation 'io.github.ujizin:camposer:0.3.0'

    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.androidx.test)
    debugImplementation(libs.bundles.androidx.compose.test)
}