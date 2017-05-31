package ua.ddovgal.brainshareApi.security

import org.springframework.security.core.Authentication

class IncompleteAuthenticationFromJWT(private val name: String) : Authentication {

    private var authenticated: Boolean = true

    override fun setAuthenticated(isAuthenticated: Boolean) {
        authenticated = isAuthenticated
    }

    override fun getName() = name

    override fun getCredentials() = null

    override fun isAuthenticated() = authenticated

    override fun getDetails() = null

    override fun getAuthorities() = null

    override fun getPrincipal() = null
}