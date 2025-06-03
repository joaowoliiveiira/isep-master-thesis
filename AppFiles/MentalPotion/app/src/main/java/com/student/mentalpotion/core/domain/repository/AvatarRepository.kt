package com.student.mentalpotion.core.domain.repository

import com.student.mentalpotion.core.domain.model.Avatar

interface AvatarRepository {
    suspend fun getAvatarById(avatarId: String): Result<Avatar>
}