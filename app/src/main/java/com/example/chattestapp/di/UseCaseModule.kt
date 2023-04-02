package com.example.chattestapp.di

import androidx.annotation.Keep
import com.example.chattestapp.domain.CheckCredentialsUseCase
import com.example.chattestapp.domain.GetCredentialsUseCase
import com.example.chattestapp.domain.ICheckCredentialsUseCase
import com.example.chattestapp.domain.IGetCredentialsUseCase
import org.koin.dsl.module

@Keep
val useCasesModule = module {
    factory<ICheckCredentialsUseCase> {
        CheckCredentialsUseCase(
            repository = get()
        )
    }
    factory<IGetCredentialsUseCase> {
        GetCredentialsUseCase(
            repository = get()
        )
    }
}