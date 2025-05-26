package com.student.mpbackoffice.features.authentication.domain.repository

import io.github.jan.supabase.auth.user.UserInfo

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun signup(email: String, password: String): Result<Unit>
    suspend fun logout()
    fun getCurrentUser(): UserInfo?
}