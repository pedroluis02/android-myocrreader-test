package com.github.pedroluis02.myocrreader1.ui

sealed class MainScreenType(val route: String) {
    object Home : MainScreenType("home")
    object Camera : MainScreenType("camera")
}
