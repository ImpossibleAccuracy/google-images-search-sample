plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = AppConfig.buildGroup("feature", "feed", "domain")

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    api(libs.kotlinx.collections)
    api(libs.kotlinx.datetime)
}
