package com.student.mentalpotion.features.activities.domain.usecase

import com.student.mentalpotion.features.activities.domain.repository.TopicRepository

class GetTopicByNameUseCase (
    private val repository: TopicRepository
) {
    // suspend operator fun invoke() = repository.getTopicByName()
}