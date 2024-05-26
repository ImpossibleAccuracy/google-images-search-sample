package com.googleimagesearch.feature.gallery

import android.Manifest
import android.app.Activity
import android.content.Context
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.view.View
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.work.*
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.navigator.currentOrThrow
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import com.googleimagesearch.domain.model.SearchResult
import com.googleimagesearch.feature.gallery.service.download.FileConstants
import com.googleimagesearch.feature.gallery.service.download.ImageDownloadWorker
import com.googleimagesearch.feature.gallery.viewmodel.GalleryIntent
import com.googleimagesearch.navigation.LocalNavigator
import com.googleimagesearch.navigation.screen.AppPage
import com.googleimagesearch.navigation.screen.viewModel
import kotlinx.collections.immutable.toPersistentList

@OptIn(ExperimentalFoundationApi::class)
data class GalleryPage(
    val selectedItem: SearchResult.GoogleImage?,
    val searchResults: List<SearchResult.GoogleImage>,
) : AppPage {
    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    override fun Content() {
        val view = LocalView.current
        val context = LocalContext.current

        val viewModel: GalleryViewModel = viewModel(searchResults.toPersistentList())

        val storagePermissions = rememberMultiplePermissionsState(
            listOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
            )
        )

        val notificationPermission = when {
            VERSION.SDK_INT >= VERSION_CODES.TIRAMISU -> {
                rememberPermissionState(
                    Manifest.permission.POST_NOTIFICATIONS
                )
            }

            else -> null
        }


        val pagerState = rememberPagerState(
            pageCount = { searchResults.size },
            initialPage = if (selectedItem == null) 0 else searchResults.indexOf(selectedItem)
        )

        LaunchedEffect(storagePermissions.allPermissionsGranted) {
            viewModel.onIntent(
                GalleryIntent.HasStoragePermissionsChange(
                    storagePermissions.allPermissionsGranted
                )
            )
        }

        HandleSystemUi(view)

        val navigator = LocalNavigator.currentOrThrow
        val uiState by viewModel.uiState.collectAsState()

        GalleryScreen(
            modifier = Modifier.fillMaxWidth(),
            pagerState = pagerState,
            uiState = uiState,
            actions = GalleryScreenActions(
                navigateUp = {
                    navigator.pop()
                },
                onSystemUiVisibilityChange = { isVisible ->
                    val activity = view.context as Activity

                    if (isVisible) {
                        disableImmersiveMode(activity)
                    } else {
                        enableImmersiveMode(activity)
                    }
                },
                startDownloadingImage = { image ->
                    if ((uiState.hasStoragePermissions ||
                                VERSION.SDK_INT >= VERSION_CODES.R) &&
                        notificationPermission?.status == PermissionStatus.Granted
                    ) {
                        startDownloadingImage(context, image)
                    } else {
                        storagePermissions.launchMultiplePermissionRequest()
                        notificationPermission?.launchPermissionRequest()
                    }
                },
            ),
        )
    }

    private fun startDownloadingImage(
        context: Context,
        image: SearchResult.GoogleImage,
    ) {
        val data = Data.Builder()

        data.apply {
            putString(FileConstants.KEY_FILE_NAME, image.imageUrl.substringAfterLast("/"))
            putString(FileConstants.KEY_FILE_URL, image.imageUrl)
        }

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresStorageNotLow(true)
            .setRequiresBatteryNotLow(true)
            .build()

        val imageDownloadWorker = OneTimeWorkRequestBuilder<ImageDownloadWorker>()
            .setConstraints(constraints)
            .setInputData(data.build())
            .build()

        val workManager = WorkManager.getInstance(context)

        workManager.enqueueUniqueWork(
            "imageDownloadWork_${System.currentTimeMillis()}",
            ExistingWorkPolicy.KEEP,
            imageDownloadWorker
        )
    }

    @Composable
    private fun HandleSystemUi(view: View) {
        LifecycleEffect(
            onStarted = {
                val activity = view.context as Activity
                enableImmersiveMode(activity)
            },
            onDisposed = {
                val activity = view.context as Activity
                disableImmersiveMode(activity)
            }
        )
    }

    private fun enableImmersiveMode(activity: Activity) {
        val window = activity.window

        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView)

        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
    }

    private fun disableImmersiveMode(activity: Activity) {
        val window = activity.window

        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView)

        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_DEFAULT

        windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
    }
}
