package com.github.pedroluis02.myocrreader1

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.pedroluis02.myocrreader1.camera.CameraPermissions
import com.github.pedroluis02.myocrreader1.camera.CameraView
import com.github.pedroluis02.myocrreader1.recognition.TextRecognizerController
import com.github.pedroluis02.myocrreader1.ui.theme.MyOCRReaderTheme
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : ComponentActivity() {
    private val tag = "MainActivity"

    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var cameraPermissions: CameraPermissions

    private lateinit var textRecognizer: TextRecognizerController

    private var shouldShowCamera: MutableState<Boolean> = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyOCRReaderTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    if (shouldShowCamera.value) {
                        CameraView(
                            outputDirectory = outputDirectory,
                            executor = cameraExecutor,
                            onImageCaptured = ::handleImageCapture,
                            onError = { Log.e(tag, "View error:", it) }
                        )
                    }
                }
            }
        }

        cameraPermissions = CameraPermissions(this)
        cameraPermissions.request {
            shouldShowCamera.value = true
        }

        outputDirectory = filesDir
        cameraExecutor = Executors.newSingleThreadExecutor()

        textRecognizer = TextRecognizerController(this)
    }

    private fun handleImageCapture(uri: Uri) {
        Log.i(tag, "Image captured: $uri")
        //shouldShowCamera.value = false
        textRecognizer.recognize(uri, {
            Log.i(tag, "Text recognition:")
            Log.i(tag, it)
        }, failure = {
            Log.e(tag, "Text recognition error:", it)
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun TextResult(text: String) {
    Text(text = text)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyOCRReaderTheme {
        Greeting("Android")
    }
}