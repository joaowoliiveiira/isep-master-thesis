package com.student.mentalpotion.features.profile.data.mapper

import com.student.mentalpotion.features.profile.domain.model.UserProfile

data class UserProfileDTO(
    val uid: String = "",
    val name: String = "",
    val email: String = ""
) {
    fun toDomain(): UserProfile = UserProfile(uid, name, email)

    companion object {
        fun fromDomain(profile: UserProfile): UserProfileDTO {
            return UserProfileDTO(
                uid = profile.uid,
                name = profile.username,
                email = profile.email
            )
        }
    }
}