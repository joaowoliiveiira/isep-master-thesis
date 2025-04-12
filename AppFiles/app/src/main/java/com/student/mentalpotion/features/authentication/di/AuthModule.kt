package com.student.mentalpotion.features.authentication.di

import com.google.firebase.auth.FirebaseAuth
import com.student.mentalpotion.features.authentication.data.repository.AuthenticationRepoImpl
import com.student.mentalpotion.features.authentication.domain.repository.AuthenticationRepository
import com.student.mentalpotion.features.authentication.domain.usecase.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton

@InstallIn
@Module
object AuthModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth
    ): AuthenticationRepository = AuthenticationRepoImpl(firebaseAuth)

    @Provides
    fun provideLoginUseCase(
        repository: AuthenticationRepository
    ) = LoginUseCase(repository)
}