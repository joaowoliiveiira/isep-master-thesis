package com.student.mentalpotion.features.authentication.di

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.student.mentalpotion.core.domain.repository.UserRepository
import com.student.mentalpotion.features.authentication.data.repository.AuthAccountRepoImpl
import com.student.mentalpotion.features.authentication.domain.usecase.LoginUseCase
import com.student.mentalpotion.features.authentication.domain.usecase.LogoutUseCase
import com.student.mentalpotion.features.authentication.domain.usecase.RegisterUseCase
import com.student.mentalpotion.features.authentication.domain.repository.FirebaseAuthService
import com.student.mentalpotion.features.authentication.data.service.FirebaseAuthServiceImpl
import com.student.mentalpotion.features.authentication.domain.repository.AuthAccountRepository
import com.student.mentalpotion.features.authentication.domain.usecase.CheckSessionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    @Singleton
    fun provideFirebaseAuthService(
        firebaseAuth: FirebaseAuth
    ): FirebaseAuthService = FirebaseAuthServiceImpl(firebaseAuth)

    @Provides
    @Singleton
    fun provideAuthenticationRepository(
        authService: FirebaseAuthService
    ): AuthAccountRepository = AuthAccountRepoImpl(authService)

    @Provides
    fun provideLoginUseCase(
        repository: AuthAccountRepository
    ): LoginUseCase = LoginUseCase(repository)

    @Provides
    fun provideRegisterUseCase(
        authRepository: AuthAccountRepository,
        userRepository: UserRepository
    ): RegisterUseCase = RegisterUseCase(authRepository, userRepository)

    @Provides
    fun provideLogoutUseCase(
        repository: AuthAccountRepository
    ): LogoutUseCase = LogoutUseCase(repository)

    @Provides
    fun provideCheckSessionUseCase(
        repository: AuthAccountRepository
    ): CheckSessionUseCase = CheckSessionUseCase(repository)
}