package com.student.mentalpotion.core.domain.model

import androidx.annotation.DrawableRes
import com.student.mentalpotion.R

data class UserAvatar(
    val userId: String,
    val avatarId: String,
    val unlockedAt: Long,
    @DrawableRes val drawableRes: Int
)

data class AvatarOption(
    val id: String,
    @DrawableRes val drawableRes: Int
)

val avatarOptions = listOf(
    AvatarOption("avatar_cat", R.drawable.default_profile),
    AvatarOption("avatar_dog", R.drawable.default_profile),
    AvatarOption("avatar_fox", R.drawable.default_profile)
)