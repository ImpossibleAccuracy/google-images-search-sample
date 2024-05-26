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
include(":presenter")

include(":feature:feed:presenter")
include(":feature:feed:domain")
include(":feature:feed:data")
include(":feature:feed:di")

include(":feature:gallery:presenter")
include(":feature:gallery:di")
