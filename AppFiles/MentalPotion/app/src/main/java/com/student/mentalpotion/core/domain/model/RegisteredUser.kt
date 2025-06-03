package com.student.mentalpotion.core.domain.model

import com.student.mentalpotion.features.authentication.domain.model.AuthUser

data class RegisteredUser(
    val authUser: AuthUser,
    val profileUser: ProfileUser
)