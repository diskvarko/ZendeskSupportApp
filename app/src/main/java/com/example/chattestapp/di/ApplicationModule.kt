package com.example.chattestapp.di

import androidx.annotation.Keep


@Keep
val applicationModules = listOf(
    repositoryModule,
    useCasesModule,
    viewModelModule
)