package com.student.mentalpotion.features.authentication.domain.usecase

import com.student.mentalpotion.core.domain.model.ProfileUser
import com.student.mentalpotion.core.domain.model.RegisteredUser
import com.student.mentalpotion.core.domain.repository.UserRepository
import com.student.mentalpotion.core.util.NetworkError
import com.student.mentalpotion.core.util.Result
import com.student.mentalpotion.features.authentication.domain.repository.AuthAccountRepository

class RegisterUseCase(
    private val authRepository: AuthAccountRepository,
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        name: String,
        avatarId: String? = null
    ): Result<RegisteredUser, NetworkError> {
        return when (val authResult = authRepository.register(email, password)) {
            is Result.Error -> Result.Error(authResult.error)

            is Result.Success -> {
                val authUser = authResult.data

                val profileUser = ProfileUser(
                    uid = authUser.uid,
                    email = authUser.email,
                    name = name,
                    avatarId = avatarId,
                    createdAt = System.currentTimeMillis()
                )

                when (val profileResult = userRepository.createUser(profileUser)) {
                    is Result.Success -> Result.Success(
                        RegisteredUser(
                            authUser = authUser,
                            profileUser = profileUser
                        )
                    )

                    is Result.Error -> Result.Error(profileResult.error)
                }
            }
        }
    }
}
