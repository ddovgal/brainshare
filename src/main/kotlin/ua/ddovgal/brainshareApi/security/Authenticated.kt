package ua.ddovgal.brainshareApi.security

import org.springframework.security.access.prepost.PreAuthorize

/**
 * Security is configured in such a way that all endpoints are public. So, if method is require authentication,
 * then it must be annotated with this annotation. Then that endpoint will be secured
 * like in antMatchers(...).authenticated() way
 */
@Target(AnnotationTarget.FUNCTION)
@PreAuthorize("isAuthenticated()")
annotation class Authenticated