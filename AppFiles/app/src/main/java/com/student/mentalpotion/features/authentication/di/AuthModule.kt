package com.student.mentalpotion.features.authentication.di

import com.google.firebase.auth.FirebaseAuth
import com.student.mentalpotion.features.authentication.data.repository.AuthenticationRepoImpl
import com.student.mentalpotion.features.authentication.domain.repository.AuthenticationRepository
import com.student.mentalpotion.features.authentication.domain.usecase.LoginUseCase
import com.student.mentalpotion.features.authentication.service.FirebaseAuthService
import com.student.mentalpotion.features.authentication.service.FirebaseAuthServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AuthModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseAuthService(
        firebaseAuth: FirebaseAuth
    ): FirebaseAuthService = FirebaseAuthServiceImpl(firebaseAuth)

    @Provides
    @Singleton
    fun provideAuthRepository(
        authService: FirebaseAuthService
    ): AuthenticationRepository = AuthenticationRepoImpl(authService)

    @Provides
    fun provideLoginUseCase(
        repository: AuthenticationRepository
    ) = LoginUseCase(repository)
}