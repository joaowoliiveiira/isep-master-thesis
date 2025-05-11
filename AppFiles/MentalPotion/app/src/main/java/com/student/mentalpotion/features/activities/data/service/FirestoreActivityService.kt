package com.student.mentalpotion.features.activities.data.service

import arrow.core.Either
import com.student.mentalpotion.features.activities.domain.model.Activity
import com.student.mentalpotion.core.util.NetworkError

interface FirestoreActivityService {
    suspend fun getActivitiesByTopic(topicId: String): Either<NetworkError, List<Activity>>
    suspend fun getActivityById(uid: String): Either<NetworkError, Activity>
}