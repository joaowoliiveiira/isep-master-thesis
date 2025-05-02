package com.student.mentalpotion.features.activities.data.mapper

import com.student.mentalpotion.features.activities.domain.model.Activity
import com.student.mentalpotion.features.activities.domain.model.DifficultyLevel
import com.student.mentalpotion.features.activities.domain.model.PhotoActivity
import com.student.mentalpotion.features.activities.domain.model.TimerActivity

data class ActivityDTO(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val topicId: String = "",
    val difficulty: String = "", // e.g., "EASY", "MEDIUM"
    val type: String = "",       // e.g., "TIMER", "PHOTO"
    val duration: Int? = null,   // For TimerActivity
    val prompt: String? = null   // For PhotoActivity
) {
    fun toDomain(): Activity {
        return when (type.uppercase()) {
            "TIMER" -> TimerActivity(
                id = id,
                title = title,
                description = description,
                topicId = topicId,
                difficulty = DifficultyLevel.valueOf(difficulty),
                durationMinutes = duration ?: 0
            )
            "PHOTO" -> PhotoActivity(
                id = id,
                title = title,
                description = description,
                topicId = topicId,
                difficulty = DifficultyLevel.valueOf(difficulty),
                prompt = prompt.orEmpty()
            )
            else -> throw IllegalArgumentException("Unknown activity type: $type")
        }
    }

    fun toDto(): ActivityDTO = when (this) {
        is TimerActivity -> ActivityDTO(
            id, title, description, topicId, difficulty.name, "TIMER", durationMinutes, null
        )
        is PhotoActivity -> ActivityDTO(
            id, title, description, topicId, difficulty.name, "PHOTO", null, prompt
        )
    }
}