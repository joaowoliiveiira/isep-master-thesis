package com.student.mpbackoffice.di

import com.student.mpbackoffice.features.authentication.data.SupabaseAuthRepository
import com.student.mpbackoffice.features.authentication.presentation.login.LoginViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val appModule = module {
    single<SupabaseClient> {
        createSupabaseClient(
            supabaseUrl = "https://jzonmltlqfsuulgzunjx.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imp6b25tbHRscWZzdXVsZ3p1bmp4Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDcwMDc3MzcsImV4cCI6MjA2MjU4MzczN30.SIBEJ1u3bSs0LSswhtLbb9eIVr8bClCZN8_Slhk_4o4"
        ) {
            install(Auth)
        }
    }

    single { SupabaseAuthRepository(get()) }
    factoryOf(::LoginViewModel)
}
