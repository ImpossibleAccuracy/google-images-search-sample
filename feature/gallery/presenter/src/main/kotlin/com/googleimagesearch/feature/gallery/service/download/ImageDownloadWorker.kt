package com.googleimagesearch.feature.gallery.service.download

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.webkit.MimeTypeMap
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.FileProvider
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.googleimagesearch.feature.gallery.R
import java.io.File
import java.io.FileOutputStream
import java.net.URL


class ImageDownloadWorker(
    private val context: Context,
    workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        val fileUrl = inputData.getString(FileConstants.KEY_FILE_URL) ?: ""
        val fileName = inputData.getString(FileConstants.KEY_FILE_NAME) ?: ""
        val extension = MimeTypeMap.getFileExtensionFromUrl(fileName)
        val mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)!!

        Log.d(ImageDownloadWorker::class.simpleName, "Downloading file: $fileUrl")

        if (fileName.isEmpty()
            || fileUrl.isEmpty()
        ) {
            return Result.failure()
        }

        showLoadingNotification()

        val uri = downloadFileAndSave(
            fileName = fileName,
            fileUrl = fileUrl,
            mimeType = mimeType,
        )

        NotificationManagerCompat.from(context).cancel(NotificationConstants.PROGRESS_NOTIFICATION_ID)


        return if (uri != null) {
            showImageSavedNotification(
                mimeType = mimeType,
                fileUri = uri
            )

            Result.success(workDataOf(FileConstants.KEY_FILE_URI to uri.toString()))
        } else {
            Result.failure()
        }
    }

    private fun downloadFileAndSave(
        fileName: String,
        fileUrl: String,
        mimeType: String,
    ): Uri? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                put(MediaStore.MediaColumns.MIME_TYPE, mimeType)
                put(MediaStore.MediaColumns.RELATIVE_PATH, FileConstants.RELATIVE_PATH)
            }

            val resolver = context.contentResolver

            return resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
                ?.also { target ->
                    URL(fileUrl).openStream().use { input ->
                        resolver.openOutputStream(target).use { output ->
                            input.copyTo(output!!, DEFAULT_BUFFER_SIZE)
                        }
                    }
                }
        } else {
            val target = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                fileName,
            )

            URL(fileUrl).openStream().use { input ->
                FileOutputStream(target).use { output ->
                    input.copyTo(output)
                }
            }

            return FileProvider.getUriForFile(
                context,
                context.applicationContext.packageName + ".provider",
                target,
            )
        }
    }

    private fun showLoadingNotification() {
        val builder = NotificationCompat.Builder(context, NotificationConstants.CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_download_24)
            .setContentTitle(context.getString(R.string.downloading_file))
            .setOngoing(true)
            .setProgress(0, 0, true)

        showNotification(builder)
    }

    private fun showImageSavedNotification(
        mimeType: String,
        fileUri: Uri,
    ) {
        val intent = Intent()
        intent.setAction(Intent.ACTION_VIEW)
        intent.setDataAndType(fileUri, mimeType)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        val builder = NotificationCompat.Builder(context, NotificationConstants.CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_download_24)
            .setContentTitle(context.getString(R.string.title_image_saved))
            .setContentInfo(context.getString(R.string.subtitle_image_saved, fileUri))
            .setAutoCancel(true)
            .setContentIntent(
                PendingIntent.getActivity(
                    context,
                    NotificationConstants.INFO_NOTIFICATION_ID,
                    intent,
                    PendingIntent.FLAG_IMMUTABLE,
                )
            )

        showNotification(builder)
    }

    private fun showNotification(notification: NotificationCompat.Builder) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        createNotificationChannel()

        NotificationManagerCompat.from(context).notify(
            NotificationConstants.INFO_NOTIFICATION_ID,
            notification
                .setChannelId(NotificationConstants.CHANNEL_ID)
                .build(),
        )
    }

    private fun createNotificationChannel() {
        val name = NotificationConstants.CHANNEL_NAME
        val description = NotificationConstants.CHANNEL_DESCRIPTION
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(NotificationConstants.CHANNEL_ID, name, importance)
        channel.description = description

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

        notificationManager?.createNotificationChannel(channel)
    }
}
