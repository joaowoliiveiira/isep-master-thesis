package com.student.mentalpotion.features.profile.di

import com.google.firebase.firestore.FirebaseFirestore
import com.student.mentalpotion.features.profile.data.repository.UserProfileRepoImpl
import com.student.mentalpotion.features.profile.data.service.FirestoreUserProfileService
import com.student.mentalpotion.features.profile.data.service.FirestoreUserProfileServiceImpl
import com.student.mentalpotion.features.profile.domain.repository.UserProfileRepository
import com.student.mentalpotion.features.profile.domain.usecase.CreateUserProfileUseCase
import com.student.mentalpotion.features.profile.domain.usecase.GetUserProfileUseCase
import com.student.mentalpotion.features.profile.domain.usecase.UpdateUserProfileUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore =
        FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideUserProfileService(
        firestore: FirebaseFirestore
    ): FirestoreUserProfileService =
        FirestoreUserProfileServiceImpl(firestore)

    @Provides
    @Singleton
    fun provideUserProfileRepository(
        service: FirestoreUserProfileService
    ): UserProfileRepository =
        UserProfileRepoImpl(service)

    @Provides
    fun provideCreateUserProfileUseCase(
        repository: UserProfileRepository
    ) = CreateUserProfileUseCase(repository)

    @Provides
    fun provideGetUserProfileUseCase(
        repository: UserProfileRepository
    ) = GetUserProfileUseCase(repository)

    @Provides
    fun provideUpdateUserProfileUseCase(
        repository: UserProfileRepository
    ) = UpdateUserProfileUseCase(repository)
}