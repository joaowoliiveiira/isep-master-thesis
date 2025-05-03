package com.student.mentalpotion.features.activities.di

import com.google.firebase.firestore.FirebaseFirestore
import com.student.mentalpotion.features.activities.data.repository.ActivityRepositoryImpl
import com.student.mentalpotion.features.activities.data.repository.TopicRepositoryImpl
import com.student.mentalpotion.features.activities.data.service.FirestoreActivityService
import com.student.mentalpotion.features.activities.data.service.FirestoreActivityServiceImpl
import com.student.mentalpotion.features.activities.data.service.FirestoreTopicService
import com.student.mentalpotion.features.activities.data.service.FirestoreTopicServiceImpl
import com.student.mentalpotion.features.activities.domain.repository.ActivityRepository
import com.student.mentalpotion.features.activities.domain.repository.TopicRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ActivityModule {

    @Provides
    @Singleton
    fun provideActivityService(
        firestore: FirebaseFirestore
    ): FirestoreActivityService =
        FirestoreActivityServiceImpl(firestore)

    @Provides
    @Singleton
    fun provideActivityRepository(
        service: FirestoreActivityService
    ): ActivityRepository =
        ActivityRepositoryImpl(service)

    /*
    @Provides
    fun provideGetActivitiesByTopicIdUseCase(
        repository: ActivityRepository
    ) = GetActivitiesByTopicIdUseCase(repository)

    @Provides
    fun provideGetActivityByIdUseCase(
        repository: ActivityRepository
    ) = GetActivityByIdUseCase(repository)

     */
}