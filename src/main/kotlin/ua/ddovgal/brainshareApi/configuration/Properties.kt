package ua.ddovgal.brainshareApi.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import javax.validation.constraints.NotNull

@Configuration
@ConfigurationProperties(prefix = "email")
//@Validated // TODO: uncomment and fix this some day
class MailProperties {
    @NotNull lateinit var fromAddress: String
}

@Configuration
@ConfigurationProperties(prefix = "security")
//@Validated
class SecurityProperties {
    val jwt = JWTProperties()

    class JWTProperties {
        @NotNull lateinit var secret: String
        @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
        @NotNull lateinit var expirationMinutes: Integer // need Integer because kotlin's Int cant be lateinit
    }
}

@Configuration
@ConfigurationProperties(prefix = "web")
//@Validated
class WebProperties {
    @NotNull lateinit var rootUrl: String
    @NotNull lateinit var protocol: String
}