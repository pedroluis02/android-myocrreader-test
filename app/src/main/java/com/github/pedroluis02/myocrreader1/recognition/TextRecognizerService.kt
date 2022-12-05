package com.github.pedroluis02.myocrreader1.recognition

import android.net.Uri

interface TextRecognizerService {
    fun init()
    fun recognize(input: Uri, success: (String) -> Unit, failure: (Exception) -> Unit)
}

