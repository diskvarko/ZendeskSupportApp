package com.example.chattestapp.domain

interface ICheckCredentialsUseCase {
    fun isMissingCredentials(): Boolean
}