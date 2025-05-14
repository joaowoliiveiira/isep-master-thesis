package com.student.mpbackoffice

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "MP_Backoffice",
    ) {
        App()
    }
}