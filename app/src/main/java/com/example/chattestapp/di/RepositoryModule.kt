package com.example.chattestapp.di

import androidx.annotation.Keep
import com.example.chattestapp.data.CredentialsRepository
import com.example.chattestapp.data.ICredentialsRepository
import org.koin.dsl.module

@Keep
val repositoryModule = module {
    factory<ICredentialsRepository> { CredentialsRepository() }
}