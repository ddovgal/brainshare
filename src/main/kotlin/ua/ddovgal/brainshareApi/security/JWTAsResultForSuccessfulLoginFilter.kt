package ua.ddovgal.brainshareApi.security

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.context.support.WebApplicationContextUtils
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAsResultForSuccessfulLoginFilter(url: String, authenticationManager: AuthenticationManager) : AbstractAuthenticationProcessingFilter(AntPathRequestMatcher(url, "POST")) {

    companion object {
        private val NAME_PARAMETER = "name"
        private val PASSWORD_PARAMETER = "password"
    }

    private var jwtAuthenticationService: JWTAuthenticationService? = null

    init {
        setAuthenticationManager(authenticationManager)
    }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val namePasswordMap = jacksonObjectMapper().readValue<Map<String, String>>(request.inputStream)
        val name = namePasswordMap[NAME_PARAMETER]
        val password = namePasswordMap[PASSWORD_PARAMETER]
        val loginAttemptAuthenticationToken = UsernamePasswordAuthenticationToken(name, password)
        return authenticationManager.authenticate(loginAttemptAuthenticationToken)
    }

    override fun successfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain?, authResult: Authentication) {
        val usersName = authResult.name

        //@Autowired is not allowed, so manually lazy injecting
        if (jwtAuthenticationService == null) {
            val servletContext = request.servletContext
            val webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext)
            jwtAuthenticationService = webApplicationContext.getBean(JWTAuthenticationService::class.java)
        }
        jwtAuthenticationService!!.addAuthenticationAsJWTToHeader(response, usersName)
    }
}