plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = AppConfig.buildGroup("domain")

dependencies {
    implementation(libs.kotlinx.coroutines.core)

    implementation(libs.paging.common)
}
