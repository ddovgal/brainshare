package ua.ddovgal.brainshareApi.security

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.context.support.WebApplicationContextUtils
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class AuthenticationByJWTFilter : GenericFilterBean() {

    private var jwtAuthenticationService: JWTAuthenticationService? = null

    override fun doFilter(request: ServletRequest, response: ServletResponse, filterChain: FilterChain) {

        //@Autowired is not allowed, so manually lazy injecting
        if (jwtAuthenticationService == null) {
            val servletContext = request.servletContext
            val webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext)
            jwtAuthenticationService = webApplicationContext.getBean(JWTAuthenticationService::class.java)
        }

        val incompleteAuthentication = jwtAuthenticationService!!.getIncompleteAuthenticationFromJWT(request as HttpServletRequest)
        SecurityContextHolder.getContext().authentication = incompleteAuthentication
        filterChain.doFilter(request, response)
    }
}