package com.github.pedroluis02.myocrreader1.base

interface TextRecognizerService {
    fun init()
    fun recognize(input: String, success: (String) -> Unit, failure: (Exception) -> Unit)
}

