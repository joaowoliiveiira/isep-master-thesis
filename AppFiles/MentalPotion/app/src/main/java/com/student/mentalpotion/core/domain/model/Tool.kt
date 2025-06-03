package com.student.mentalpotion.core.domain.model

enum class ToolType { PODCAST, GUIDE, MUSIC, VIDEO }

data class Tool(
    val id: String,
    val topicId: String,
    val type: ToolType,
    val title: String,
    val contentUrl: String
)