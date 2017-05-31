package ua.ddovgal.brainshareApi.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityWebConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.headers().cacheControl()

        http.csrf().disable() // disable csrf for our requests.
                .authorizeRequests()
                // left it so I did not forget to leave it public
                .antMatchers(HttpMethod.POST, "/register").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                // We have no protected(antMatchers(...).authenticated()), because we use protection
                // by adding @Authenticated annotation to needed endpoint. So all the endpoints without @Authenticated
                // are public and don't need any authentication
                .anyRequest().permitAll()
                .and()
                // We filter the api/login requests
                .addFilterBefore(JWTAsResultForSuccessfulLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter::class.java)
                // And filter other requests to check the presence of JWT in header
                .addFilterBefore(AuthenticationByJWTFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }

    @Autowired private lateinit var userDetailsService: UserDetailsService

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(BCryptPasswordEncoder())
    }
}