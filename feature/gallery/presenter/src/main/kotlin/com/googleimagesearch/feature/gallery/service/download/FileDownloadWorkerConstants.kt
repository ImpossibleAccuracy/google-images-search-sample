package com.googleimagesearch.feature.gallery.service.download

object FileConstants {
    const val KEY_FILE_URL = "key_file_url"
    const val KEY_FILE_NAME = "key_file_name"
    const val KEY_FILE_URI = "key_file_uri"
    const val RELATIVE_PATH = "Download"
}

object NotificationConstants {
    const val CHANNEL_NAME = "Image save"
    const val CHANNEL_DESCRIPTION = "Saves image from gallery to device"
    const val CHANNEL_ID = "download_image_worker"

    const val PROGRESS_NOTIFICATION_ID = 300
    const val INFO_NOTIFICATION_ID = 200
}
