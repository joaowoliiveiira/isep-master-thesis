package com.student.mpbackoffice.app

import kotlinx.serialization.Serializable

sealed interface Route {

    // Login Flow
    @Serializable
    data object LoginGraph: Route

    @Serializable
    data object LoginPage: Route

    @Serializable
    data object SignupPage: Route


    // Manage Flow
    @Serializable
    data object ManageGraph: Route

    @Serializable
    data object HomePage: Route

    @Serializable
    data object TopicsPage: Route
}