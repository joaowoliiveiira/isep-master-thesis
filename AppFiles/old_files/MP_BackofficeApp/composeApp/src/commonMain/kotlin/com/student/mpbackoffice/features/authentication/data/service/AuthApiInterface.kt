package com.student.mpbackoffice.features.authentication.data.service

import arrow.core.Either
import com.student.mpbackoffice.core.util.NetworkError
import com.student.mpbackoffice.features.authentication.domain.model.Admin
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.flow.Flow

/*
 * For more info on Supabase for Kotlin:
 * https://supabase.com/docs/reference/kotlin/initializing
 * https://github.com/supabase-community/supabase-kt
 */

interface AuthApiInterface {

    suspend fun signIn(email: String, password: String): Either<NetworkError, Unit>

    suspend fun signUp(email: String, password: String)

    suspend fun verifyOtp(email: String, otp: String)

    suspend fun signOut()

    suspend fun resetPassword(email: String)

    suspend fun changePassword(newPassword: String)

    fun sessionStatus(): Flow<SessionStatus>
}
