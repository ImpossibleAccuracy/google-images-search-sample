enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "GoogleImageSearch"

include(":core:data")
include(":core:domain")
include(":core:di")
include(":feature:common:navigation")

include(":feature:feed:presenter")
include(":feature:feed:di")

include(":presenter")
