package com.example.chattestapp.data


class CredentialsRepository : ICredentialsRepository {
    override fun getCredentials(): String {
        return Credentials.CHAT_ACCOUNT_KEY
    }

    override fun isMissingCredentials(): Boolean {
        return Credentials.CHAT_ACCOUNT_KEY.isEmpty() || Credentials.SUBDOMAIN_URL.isEmpty()
                || Credentials.APPLICATION_ID.isEmpty() || Credentials.OAUTH_CLIENT_ID.isEmpty()
    }
}