package com.github.pedroluis02.myocrreader1.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.pedroluis02.myocrreader1.recognition.TextRecognizerController
import kotlinx.coroutines.launch

@Composable
fun MainView(navController: NavController, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()
    val textRecognizer = remember { TextRecognizerController(context) }
    var resultText by remember { mutableStateOf("") }

    val cameraViewResult = navController.getOnceCurrentCameraResult()
    if (cameraViewResult != null) {
        Log.i("App", "imagePath: $cameraViewResult")
        LaunchedEffect(Unit) {
            coroutineScope.launch {
                textRecognizer.recognize(cameraViewResult, success = {
                    resultText = it
                }, failure = { Log.e("Recognition", "error: $it") })
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar {
                Text(text = "My ORC Reader")
            }
        }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(it),
        ) {
            Text("OCR Reader", style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                navController.navigate(MainScreenType.Camera.route)
            }) {
                Text(text = "Open camera")
            }

            if (resultText.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(resultText)
            }
        }
    }
}