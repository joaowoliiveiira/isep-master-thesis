package com.student.mentalpotion.features.authentication.domain.usecase

import arrow.core.Either
import com.student.mentalpotion.core.util.ApiError
import com.student.mentalpotion.core.util.NetworkError
import com.student.mentalpotion.core.domain.model.User
import com.student.mentalpotion.features.authentication.domain.repository.AuthenticationRepository
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.StringSpec
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest


class LoginUseCaseTest : StringSpec({

    val repository = mockk<AuthenticationRepository>()
    val useCase = LoginUseCase(repository)

    "should return Right(User) on successful login" {
        val expectedUser = User("abc", "test@example.com")
        coEvery { repository.login("test@example.com", "pass") } returns Either.Right(expectedUser)

        runTest {
            val result = useCase("test@example.com", "pass")
            result.shouldBeRight(expectedUser)
        }
    }

    "should return Left(NetworkError) on login failure" {
        val error = NetworkError(ApiError.UnknownError, Exception("Oops"))
        coEvery { repository.login("fail@example.com", "wrong") } returns Either.Left(error)

        runTest {
            val result = useCase("fail@example.com", "wrong")
            result.shouldBeLeft(error)
        }
    }
})