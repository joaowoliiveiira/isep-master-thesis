package com.student.mpbackoffice.features.authentication.domain.repository

import com.student.mpbackoffice.core.domain.DataError
import com.student.mpbackoffice.core.domain.EmptyResult
import com.student.mpbackoffice.core.domain.Result
import io.github.jan.supabase.auth.user.UserInfo

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit, DataError.Remote>
    suspend fun signup(email: String, password: String): Result<Unit, DataError.Remote>
    suspend fun logout()
    fun getCurrentUser(): UserInfo?
}