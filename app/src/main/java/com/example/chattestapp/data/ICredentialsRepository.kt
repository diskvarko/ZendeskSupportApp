package com.example.chattestapp.data

interface ICredentialsRepository {
    fun getCredentials(): String
    fun isMissingCredentials(): Boolean
}