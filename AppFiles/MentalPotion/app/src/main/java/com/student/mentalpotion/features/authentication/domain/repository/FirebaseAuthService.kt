package com.student.mentalpotion.features.authentication.domain.repository

import com.student.mentalpotion.core.util.Result
import com.student.mentalpotion.core.util.NetworkError
import com.student.mentalpotion.features.authentication.domain.model.AuthUser

interface FirebaseAuthService {
    suspend fun login(email: String, password: String): Result<AuthUser, NetworkError>
    suspend fun register(email: String, password: String): Result<AuthUser, NetworkError>
    suspend fun resetPassword(email: String): Result<Unit, NetworkError>
    fun logout()

    fun isLoggedIn(): Boolean
    suspend fun getCurrentAccount(): Result<AuthUser, NetworkError>
    fun getUserIdOrNull(): String?
}