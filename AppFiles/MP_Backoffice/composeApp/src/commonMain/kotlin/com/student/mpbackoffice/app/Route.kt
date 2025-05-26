package com.student.mpbackoffice.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object LoginGraph: Route

    @Serializable
    data object LoginPage: Route

    @Serializable
    data object SignupPage: Route

    @Serializable
    data object HomePage: Route
}