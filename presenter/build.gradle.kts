import java.util.*

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = AppConfig.APPLICATION_ID
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = AppConfig.APPLICATION_ID
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.compileSdk.get().toInt()
        versionCode = AppConfig.VERSION_CODE
        versionName = AppConfig.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }

        val properties = Properties()
        // TODO: change "gradle.properties" to "local.properties"
        val propertiesFile = project.rootProject.file("gradle.properties")
        properties.load(propertiesFile.inputStream())
        buildConfigField("String", "SERPER_API_KEY", properties.getProperty("SERPER_API_KEY"))
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
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerExtensionVersion.get()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(Modules.DOMAIN))
    implementation(project(Modules.DI))
    implementation(project(Modules.NAVIGATION))

    implementation(project(Modules.FEATURE_FEED.PRESENTER))
    implementation(project(Modules.FEATURE_FEED.DI))

    implementation(project(Modules.FEATURE_GALLERY.PRESENTER))
    implementation(project(Modules.FEATURE_GALLERY.DI))

    implementation(platform(libs.compose.bom))
    implementation(libs.compose.activity)
    implementation(libs.compose.material3)

    implementation(libs.koin.core)
    implementation(libs.koin.android)

    implementation(libs.voyager.navigator)
    implementation(libs.voyager.transitions)
    implementation(libs.voyager.koin)

    implementation(libs.coil)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
}
