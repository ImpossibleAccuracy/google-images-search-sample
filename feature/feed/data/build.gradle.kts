plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)

    alias(libs.plugins.sqldelight)
}

group = AppConfig.buildGroup("feature", "feed", "data")

android {
    namespace = AppConfig.APPLICATION_ID
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
}

sqldelight {
    databases {
        create("FeedDatabase") {
            packageName.set("com.googleimagesearch.feature.feed.data")
        }
    }
}

dependencies {
    implementation(project(Modules.DOMAIN))
    implementation(project(Modules.FEATURE_FEED.DOMAIN))

    implementation(libs.kotlinx.coroutines.core)

    implementation(libs.paging.common)
    implementation(libs.apiresult)

    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.gson)
    api(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.retrofit.coroutines)

    implementation(libs.sqldelight.android)
    implementation(libs.sqldelight.coroutines)

//    implementation(libs.room.runtime)
//    ksp(libs.room.compiler)
}
