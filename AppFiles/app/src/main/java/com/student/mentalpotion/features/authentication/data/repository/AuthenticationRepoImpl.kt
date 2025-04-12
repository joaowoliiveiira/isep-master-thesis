package com.student.mentalpotion.features.authentication.data.repository

import arrow.core.Either
import com.google.firebase.auth.FirebaseAuth
import com.student.mentalpotion.features.authentication.domain.model.NetworkError
import com.student.mentalpotion.features.authentication.domain.model.User
import com.student.mentalpotion.features.authentication.domain.repository.AuthenticationRepository

class AuthenticationRepoImpl (
    private val firebaseAuth: FirebaseAuth
    ) : AuthenticationRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Either<NetworkError, User> {
        TODO("Not yet implemented")
    }

    override suspend fun register(
        email: String,
        password: String
    ): Either<NetworkError, User> {
        TODO("Not yet implemented")
    }

    override fun logout() {
        TODO("Not yet implemented")
    }

    override fun getCurrentUser(): User? {
        TODO("Not yet implemented")
    }

}