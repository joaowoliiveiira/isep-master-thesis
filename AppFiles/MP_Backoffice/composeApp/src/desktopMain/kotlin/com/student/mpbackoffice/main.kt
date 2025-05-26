package com.student.mpbackoffice

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.student.mpbackoffice.app.App
import com.student.mpbackoffice.di.initKoin

fun main() = application {

    // Initialize Koin
    initKoin()

    Window(
        onCloseRequest = ::exitApplication,
        title = "MP_Backoffice",
    ) {
        App()
    }
}