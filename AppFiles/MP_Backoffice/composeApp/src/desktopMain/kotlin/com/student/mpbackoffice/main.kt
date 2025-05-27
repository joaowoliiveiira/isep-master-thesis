package com.student.mpbackoffice

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.student.mpbackoffice.app.App
import com.student.mpbackoffice.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "CMP-Bookpedia",
        ) {
            App()
        }
    }
}