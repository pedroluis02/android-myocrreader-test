package com.github.pedroluis02.myocrreader1.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.github.pedroluis02.myocrreader1.camera.CameraView
import java.util.concurrent.Executors

private const val CAMERA_VIEW_RESULT = "camera-view.result"

@Composable
fun MainCameraView(navController: NavController) {
    val context = LocalContext.current
    val executor = Executors.newSingleThreadExecutor()

    CameraView(
        outputDirectory = context.filesDir,
        executor = executor,
        onImageCaptured = {
            navController.setResultAndPopPreviousNav(CAMERA_VIEW_RESULT, it)
        },
        onError = {},
    )

    DisposableEffect(key1 = executor) {
        onDispose {
            executor.shutdown()
        }
    }
}

@Composable
fun NavController.getOnceCurrentCameraResult(): String? {
    return getOnceCurrentNavResult(CAMERA_VIEW_RESULT)
}
