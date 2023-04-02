package com.example.chattestapp.di

import androidx.annotation.Keep
import com.example.chattestapp.domain.GetCredentialsUseCase
import com.example.chattestapp.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@Keep
val viewModelModule = module() {
    viewModel {
        MainViewModel(get(), get())
    }
}