data class UiFeature(
    val path: String
)

val UiFeature.DI
    get() = "$path:di"

val UiFeature.PRESENTER
    get() = "$path:presenter"
