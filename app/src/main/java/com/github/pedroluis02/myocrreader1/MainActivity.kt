package com.github.pedroluis02.myocrreader1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import com.github.pedroluis02.myocrreader1.camera.CameraPermissions
import com.github.pedroluis02.myocrreader1.ui.MainNavGraph
import com.github.pedroluis02.myocrreader1.ui.theme.MyOCRReaderTheme

class MainActivity : ComponentActivity() {
    private lateinit var cameraPermissions: CameraPermissions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyOCRReaderTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainNavGraph()
                }
            }
        }

        cameraPermissions = CameraPermissions(this)
        cameraPermissions.request {}
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyOCRReaderTheme {
        Greeting("Android")
    }
}