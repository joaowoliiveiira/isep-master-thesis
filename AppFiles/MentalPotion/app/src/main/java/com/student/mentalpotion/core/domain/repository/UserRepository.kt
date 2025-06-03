package com.student.mentalpotion.core.domain.repository

import com.student.mentalpotion.core.domain.model.ProfileUser
import com.student.mentalpotion.core.util.NetworkError
import com.student.mentalpotion.core.util.Result

interface UserRepository {
    suspend fun createUser(user: ProfileUser): Result<Unit, NetworkError>
    suspend fun getCurrentUserProfile(): Result<ProfileUser, NetworkError>
}