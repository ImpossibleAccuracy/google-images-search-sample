package com.googleimagesearch.feature.gallery.service.download

class ImageDownloadException : RuntimeException {
    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
}
