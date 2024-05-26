plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

group = AppConfig.buildGroup("feature", "feed", "di")

android {
    namespace = AppConfig.buildGroup("feature", "feed", "di")
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.jvmTargetVersion.get())
        targetCompatibility = JavaVersion.toVersion(libs.versions.jvmTargetVersion.get())
    }

    kotlinOptions {
        jvmTarget = libs.versions.jvmTargetVersion.get()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerExtensionVersion.get()
    }
}

dependencies {
    implementation(project(Modules.DOMAIN))
    implementation(project(Modules.FEATURE_FEED.DOMAIN))
    implementation(project(Modules.FEATURE_FEED.DATA))
    implementation(project(Modules.FEATURE_FEED.PRESENTER))

    implementation(libs.compose.runtime)

    implementation(libs.koin.core)
}
