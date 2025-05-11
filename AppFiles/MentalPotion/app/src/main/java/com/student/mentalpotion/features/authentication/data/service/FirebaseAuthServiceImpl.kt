package com.student.mentalpotion.features.authentication.data.service

import arrow.core.Either
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.student.mentalpotion.features.authentication.data.mapper.toNetworkError
import com.student.mentalpotion.features.authentication.domain.model.User
import com.student.mentalpotion.core.util.NetworkError
import kotlinx.coroutines.tasks.await

class FirebaseAuthServiceImpl(
    private val firebaseAuth: FirebaseAuth = Firebase.auth
) : FirebaseAuthService {

    override suspend fun login(email: String, password: String): Either<NetworkError, User> {
        return Either.catch {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val firebaseUser = result.user ?: throw Exception("User is null")
            User(firebaseUser.uid, firebaseUser.email ?: "")
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun register(email: String, password: String): Either<NetworkError, User> {
        return Either.catch {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = result.user ?: throw Exception("User is null")
            User(firebaseUser.uid, firebaseUser.email ?: "")
        }.mapLeft { it.toNetworkError() }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }

    override fun getCurrentUser(): Either<Throwable, User> {
        return Either.catch {
            val user = firebaseAuth.currentUser ?: throw Exception("No user logged in")
            User(user.uid, user.email ?: "")
        }
    }

    override suspend fun resetPassword(email: String): Either<NetworkError, Unit> {
        return Either.catch {
            firebaseAuth.sendPasswordResetEmail(email).await()
            Unit
        }.mapLeft { it.toNetworkError() }
    }
}
