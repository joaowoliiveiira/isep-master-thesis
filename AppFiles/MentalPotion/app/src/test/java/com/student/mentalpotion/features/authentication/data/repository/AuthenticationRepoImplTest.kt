package com.student.mentalpotion.features.authentication.data.repository

import com.student.mentalpotion.core.util.ApiError
import com.student.mentalpotion.core.util.NetworkError
import com.student.mentalpotion.features.authentication.domain.model.AuthUser
import com.student.mentalpotion.features.authentication.fakes.FakeFirebaseAuthService
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.StringSpec
import kotlinx.coroutines.test.runTest

class AuthenticationRepoImplTest : StringSpec({

    val fakeService = FakeFirebaseAuthService()
    val repository = AuthenticationRepoImpl(fakeService)

    "login returns Right(User) when service succeeds" {
        fakeService.shouldFail = false

        runTest {
            val result = repository.login("test@example.com", "password")
            result.shouldBeRight(AuthUser("123", "test@example.com"))
        }
    }

    "login returns Left(NetworkError) when service fails" {
        fakeService.shouldFail = true
        fakeService.failWith = Exception("Boom!")

        runTest {
            val result = repository.login("test@example.com", "password")
            result.shouldBeLeft(
                NetworkError(ApiError.UnknownError, fakeService.failWith)
            )
        }
    }

    "getCurrentUser returns Right when user is logged in" {
        fakeService.loggedInUser = AuthUser("999", "me@example.com")

        val result = repository.getCurrentUser()
        result.shouldBeRight(AuthUser("999", "me@example.com"))
    }

    "getCurrentUser returns Left when no user is logged in" {
        fakeService.loggedInUser = null

        val result = repository.getCurrentUser()
        result.shouldBeLeft()
    }
})