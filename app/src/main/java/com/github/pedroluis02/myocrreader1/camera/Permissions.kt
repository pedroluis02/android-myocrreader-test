package com.github.pedroluis02.myocrreader1.camera

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class CameraPermissions(private val activity: ComponentActivity) {
    private val tag = "camera-permissions"
    private fun createRequestPermissionLauncher(success: () -> Unit): ActivityResultLauncher<String> {
        return activity.registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                Log.i(tag, "Permission granted")
                success()
            } else {
                Log.i(tag, "Permission denied")
            }
        }
    }

    fun request(success: () -> Unit) {
        when {
            ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.i(tag, "Permission previously granted")
                success()
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                Manifest.permission.CAMERA
            ) -> Log.i(tag, "Show camera permissions dialog")

            else -> {
                val requestPermissionLauncher = createRequestPermissionLauncher(success)
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }
}