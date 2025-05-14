package com.student.mpbackoffice.features.authentication.data.service

import arrow.core.Either
import com.student.mpbackoffice.core.util.NetworkError
import com.student.mpbackoffice.features.authentication.domain.model.Admin
import com.student.mpbackoffice.features.authentication.data.mapper.toNetworkError
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.flow.Flow

/*
 * For more info on Supabase for Kotlin:
 * https://supabase.com/docs/reference/kotlin/initializing
 * https://github.com/supabase-community/supabase-kt
 */

class AuthApiImpl(
    private val client: SupabaseClient
) : AuthApiInterface {

    private val auth = client.auth

    override fun sessionStatus(): Flow<SessionStatus> {
        return auth.sessionStatus
    }

    override suspend fun verifyOtp(email: String, otp: String) {
        auth.verifyEmailOtp(OtpType.Email.EMAIL, email, otp)
    }

    override suspend fun signIn(email: String, password: String): Either<NetworkError, Unit> {
        return Either.catch {
            auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun signUp(email: String, password: String) {
        auth.signUpWith(Email) {
            this.email = email
            this.password = password
        }
    }

    override suspend fun changePassword(newPassword: String) {
        auth.updateUser {
            this.password = newPassword
        }
    }

    override suspend fun signOut() {
        auth.signOut()
    }

    override suspend fun resetPassword(email: String) {
        auth.resetPasswordForEmail(email)
    }
}