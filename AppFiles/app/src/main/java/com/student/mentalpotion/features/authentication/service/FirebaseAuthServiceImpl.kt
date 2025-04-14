package com.student.mentalpotion.features.authentication.service

import arrow.core.Either
import com.google.firebase.auth.FirebaseAuth
import com.student.mentalpotion.features.authentication.data.mapper.toNetworkError
import com.student.mentalpotion.features.authentication.domain.model.User
import com.student.mentalpotion.features.authentication.domain.model.NetworkError
import kotlinx.coroutines.tasks.await

class FirebaseAuthServiceImpl(
    private val firebaseAuth: FirebaseAuth
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
}
