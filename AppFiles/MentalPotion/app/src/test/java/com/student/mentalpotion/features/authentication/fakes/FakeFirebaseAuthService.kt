package com.student.mentalpotion.features.authentication.fakes

import arrow.core.Either
import com.student.mentalpotion.core.util.ApiError
import com.student.mentalpotion.core.util.NetworkError
import com.student.mentalpotion.features.authentication.domain.model.User
import com.student.mentalpotion.features.authentication.data.service.FirebaseAuthService

class FakeFirebaseAuthService : FirebaseAuthService {

    var shouldFail = false
    var failWith: Throwable = Exception("Default failure")
    var loggedInUser: User? = null

    override suspend fun login(email: String, password: String): Either<NetworkError, User> {
        return if (shouldFail) {
            Either.Left(NetworkError(ApiError.UnknownError, failWith))
        } else {
            val user = User("123", email)
            loggedInUser = user
            Either.Right(user)
        }
    }

    override suspend fun register(email: String, password: String): Either<NetworkError, User> {
        return if (shouldFail) {
            Either.Left(NetworkError(ApiError.UnknownError, failWith))
        } else {
            val user = User("456", email)
            loggedInUser = user
            Either.Right(user)
        }
    }

    override fun logout() {
        loggedInUser = null
    }

    override fun getCurrentUser(): Either<Throwable, User> {
        return loggedInUser?.let {
            Either.Right(it)
        } ?: Either.Left(Exception("No user logged in"))
    }
}