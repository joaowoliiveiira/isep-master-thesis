package com.student.mpbackoffice.features.authentication.domain.repository

import com.student.mpbackoffice.features.authentication.domain.Admin
import io.github.jan.supabase.auth.user.UserInfo

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun logout()
    fun getCurrentUser(): UserInfo?
}