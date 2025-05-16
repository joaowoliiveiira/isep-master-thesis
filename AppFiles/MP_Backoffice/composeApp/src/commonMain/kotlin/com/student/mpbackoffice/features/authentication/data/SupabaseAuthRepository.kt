package com.student.mpbackoffice.features.authentication.data

import com.student.mpbackoffice.features.authentication.domain.repository.AuthRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.user.UserSession
import io.github.jan.supabase.exceptions.HttpRequestException

class SupabaseAuthRepository(
    private val client: SupabaseClient
) : AuthRepository {

    private val auth: Auth
        get() = client.auth

    override suspend fun login(email: String, password: String): Result<Unit> {
        return try {
            auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            Result.success(Unit)
        } catch (e: HttpRequestException) {
            Result.failure(e)
        }
    }

    override suspend fun logout() {
        auth.signOut()
    }

    override fun getCurrentUser() = auth.currentUserOrNull()
}