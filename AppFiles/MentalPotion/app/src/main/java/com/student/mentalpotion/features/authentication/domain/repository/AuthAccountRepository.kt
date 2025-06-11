package com.student.mentalpotion.features.authentication.domain.repository

import com.student.mentalpotion.core.util.NetworkError
import com.student.mentalpotion.core.util.Result
import com.student.mentalpotion.features.authentication.domain.model.AuthUser

interface AuthAccountRepository {
    suspend fun login(email: String, password: String): Result<AuthUser, NetworkError>
    suspend fun register(email: String, password: String): Result<AuthUser, NetworkError>
    fun logout()
    suspend fun resetPassword(email: String): Result<Unit, NetworkError>

    fun isLoggedIn(): Boolean
    suspend fun getCurrentUser(): Result<AuthUser, NetworkError>
}
