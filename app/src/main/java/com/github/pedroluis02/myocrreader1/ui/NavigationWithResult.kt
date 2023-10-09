package com.github.pedroluis02.myocrreader1.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun <T> NavController.setResultAndPopPreviousNav(keyResult: String, result: T) {
    CoroutineScope(Dispatchers.Main).launch {
        setResultForPreviousNav(keyResult, result)
        popBackStack()
    }
}

@Composable
fun <T> NavController.getOnceCurrentNavResult(keyResult: String): T? {
    return getValueFromSavedStateHandle(keyResult, currentBackStackEntry?.savedStateHandle, true)
}

fun <T> NavController.setResultForPreviousNav(keyResult: String, result: T) {
    previousBackStackEntry?.savedStateHandle?.set<T>(keyResult, result)
}

private fun <T> getValueFromSavedStateHandle(
    key: String,
    savedStateHandle: SavedStateHandle?,
    readOnce: Boolean = false,
): T? {
    val result = savedStateHandle?.get<T>(key)
    if (readOnce) {
        savedStateHandle?.remove<T>(key)
    }

    return result
}

