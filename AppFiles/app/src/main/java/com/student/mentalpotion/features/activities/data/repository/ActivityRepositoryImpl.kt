package com.student.mentalpotion.features.activities.data.repository

import arrow.core.Either
import com.student.mentalpotion.features.activities.data.service.FirestoreActivityService
import com.student.mentalpotion.features.activities.domain.model.Activity
import com.student.mentalpotion.features.activities.domain.repository.ActivityRepository
import com.student.mentalpotion.features.authentication.domain.model.NetworkError

class ActivityRepositoryImpl(
    private val service: FirestoreActivityService
) : ActivityRepository{

    override suspend fun getActivitiesByTopic(topicId: String): Either<NetworkError, List<Activity>> {
        return service.getActivitiesByTopic(topicId)
    }

    override suspend fun getActivityById(uid: String): Either<NetworkError, Activity> {
        return service.getActivityById(uid)
    }
}