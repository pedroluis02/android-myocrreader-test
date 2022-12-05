package com.github.pedroluis02.myocrreader1.recognition.mlkit

import android.content.Context
import android.net.Uri
import com.github.pedroluis02.myocrreader1.recognition.TextRecognizerService
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

class TextRecognizerServiceImpl(private val context: Context) : TextRecognizerService {
    private var recognizer: TextRecognizer? = null

    override fun init() {
        recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
    }

    override fun recognize(
        input: Uri,
        success: (String) -> Unit,
        failure: (Exception) -> Unit
    ) {
        if (recognizer == null) {
            init()
        }

        val imageInput = InputImage.fromFilePath(context, input)
        recognizer?.process(imageInput)
            ?.addOnSuccessListener { success(it.text) }
            ?.addOnFailureListener(failure)
    }
}