package com.student.mentalpotion.features.activities.di

import com.google.firebase.firestore.FirebaseFirestore
import com.student.mentalpotion.features.activities.data.repository.TopicRepositoryImpl
import com.student.mentalpotion.features.activities.data.service.FirestoreTopicService
import com.student.mentalpotion.features.activities.data.service.FirestoreTopicServiceImpl
import com.student.mentalpotion.features.activities.domain.repository.TopicRepository
import com.student.mentalpotion.features.activities.domain.usecase.GetAllTopicsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TopicModule {

    @Provides
    @Singleton
    fun provideTopicService(
        firestore: FirebaseFirestore
    ): FirestoreTopicService =
        FirestoreTopicServiceImpl(firestore)

    @Provides
    @Singleton
    fun provideTopicRepository(
        service: FirestoreTopicService
    ): TopicRepository =
        TopicRepositoryImpl(service)

    @Provides
    fun provideGetAllTopicsUseCase(
        repository: TopicRepository
    ) = GetAllTopicsUseCase(repository)

    /*
    @Provides
    fun provideGetTopicByNameUseCase(
        repository: TopicRepository
    ) = GetTopicByNameUseCase(repository)
     */
}