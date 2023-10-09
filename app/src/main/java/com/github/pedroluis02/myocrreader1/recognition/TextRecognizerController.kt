package com.github.pedroluis02.myocrreader1.recognition

import android.content.Context
import com.github.pedroluis02.myocrreader1.base.TextRecognizerService

class TextRecognizerController(context: Context) {
    private val service: TextRecognizerService

    init {
        service = MlKitTextRecognizerService(context)
        service.init()
    }

    fun recognize(input: String, success: (String) -> Unit, failure: (Exception) -> Unit) {
        service.recognize(input, success, failure)
    }
}