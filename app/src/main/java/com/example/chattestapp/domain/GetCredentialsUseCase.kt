package com.example.chattestapp.domain

import com.example.chattestapp.data.ICredentialsRepository

class GetCredentialsUseCase(
    private val repository: ICredentialsRepository
): IGetCredentialsUseCase {

    override fun getCredentials(): String {
        return repository.getCredentials()
    }
}