package com.example.chattestapp.domain

import com.example.chattestapp.data.ICredentialsRepository

class CheckCredentialsUseCase(
    private val repository: ICredentialsRepository) : ICheckCredentialsUseCase {

    override fun isMissingCredentials(): Boolean {
        return repository.isMissingCredentials()
    }
}