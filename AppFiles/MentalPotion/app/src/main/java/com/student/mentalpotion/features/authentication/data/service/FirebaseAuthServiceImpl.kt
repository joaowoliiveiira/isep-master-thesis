package com.student.mentalpotion.features.authentication.data.service

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.student.mentalpotion.core.util.Result
import com.student.mentalpotion.core.util.NetworkError
import com.student.mentalpotion.core.util.runFirebaseCatching
import com.student.mentalpotion.features.authentication.domain.model.AuthUser
import com.student.mentalpotion.features.authentication.domain.repository.FirebaseAuthService
import kotlinx.coroutines.tasks.await

class FirebaseAuthServiceImpl(
    private val firebaseAuth: FirebaseAuth = Firebase.auth
) : FirebaseAuthService {

    override suspend fun login(email: String, password: String): Result<AuthUser, NetworkError> {
        return runFirebaseCatching {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val user = result.user ?: throw Exception("User is null")
            AuthUser(user.uid, user.email ?: "")
        }
    }

    override suspend fun register(email: String, password: String): Result<AuthUser, NetworkError> {
        return runFirebaseCatching {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val user = result.user ?: throw Exception("User is null")
            AuthUser(user.uid, user.email ?: "")
        }
    }

    override suspend fun resetPassword(email: String): Result<Unit, NetworkError> {
        return runFirebaseCatching {
            firebaseAuth.sendPasswordResetEmail(email).await()
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }

    override fun isLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override suspend fun getCurrentAccount(): Result<AuthUser, NetworkError> {
        return runFirebaseCatching {
            val user = firebaseAuth.currentUser ?: throw Exception("No user logged in")
            AuthUser(user.uid, user.email ?: "")
        }
    }

    override fun getUserIdOrNull(): String? {
        return firebaseAuth.currentUser?.uid
    }
}
