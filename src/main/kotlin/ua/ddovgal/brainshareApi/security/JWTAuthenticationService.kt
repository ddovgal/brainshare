package ua.ddovgal.brainshareApi.security

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import ua.ddovgal.brainshareApi.configuration.SecurityProperties
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service
class JWTAuthenticationService(private val securityProperties: SecurityProperties) {

    private val tokenPrefix = "Bearer"
    private val headerString = "Authorization"

    fun addAuthenticationAsJWTToHeader(response: HttpServletResponse, username: String) {
        // We generate a token now.
        val JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(Date(System.currentTimeMillis() + securityProperties.jwt.expirationMinutes.toInt()))
                .signWith(SignatureAlgorithm.HS512, securityProperties.jwt.secret)
                .compact()
        response.addHeader(headerString, tokenPrefix + " " + JWT)
    }

    fun getIncompleteAuthenticationFromJWT(request: HttpServletRequest): Authentication? {
        request.getHeader(headerString)?.let {
            try {
                Jwts.parser()
                        .setSigningKey(securityProperties.jwt.secret)
                        .parseClaimsJws(it.removePrefix(tokenPrefix + " "))
                        .body
                        .subject.let { name -> return IncompleteAuthenticationFromJWT(name) }
            } catch(e: Exception) {
                when (e) {
                    is JwtException, is IllegalArgumentException -> return null
                    else -> throw e
                }
            }
        }
        return null
    }
}