package com.github.pedroluis02.myocrreader1.camera

import android.net.Uri
import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import java.io.File
import java.util.UUID
import java.util.concurrent.Executor

class CameraPhotoTaker(
    private val imageCapture: ImageCapture,
    private val executor: Executor,
    private val outputDirectory: File,
) {
    fun take(onImageCaptured: (Uri) -> Unit, onError: (ImageCaptureException) -> Unit) {
        val photoFile = File(outputDirectory, "$newFileName.jpg")
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture.takePicture(
            outputOptions,
            executor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exception: ImageCaptureException) {
                    Log.e("PhotoTaker", "Take photo error:", exception)
                    onError(exception)
                }

                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(photoFile)
                    onImageCaptured(savedUri)
                }
            })
    }

    private val newFileName: String
        get() = UUID.randomUUID().toString()
}