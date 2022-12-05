package com.github.pedroluis02.myocrreader1.recognition

import android.content.Context
import android.net.Uri
import com.github.pedroluis02.myocrreader1.recognition.mlkit.TextRecognizerServiceImpl

class TextRecognizerController(context: Context) {
    private val service: TextRecognizerService

    init {
        service = TextRecognizerServiceImpl(context)
        service.init()
    }

    fun recognize(input: Uri, success: (String) -> Unit, failure: (Exception) -> Unit) {
        service.recognize(input, success, failure)
    }
}