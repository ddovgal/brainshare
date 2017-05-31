package ua.ddovgal.brainshareApi.exception

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.web.bind.annotation.ControllerAdvice

/**
 * If there is handler of top Exception class, which is most wide handler, it will be chosen for handling
 * any exception type(even if there are exactly typed handler for such exception class). To fix this we must set
 * a highest order to all other handlers. All handlers have by default Ordered.LOWEST_PRECEDENCE. So
 * if only Handler<Exception> will have default LOWEST_PRECEDENCE and all other Handler<SomeSpecificException>
 * will have HIGHEST_PRECEDENCE, Handler<Exception> will be at the last position of handlers chain
 * (then it will be used in the last place) and it solves original problem.
 */
@Target(AnnotationTarget.CLASS)
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
annotation class ControllerAdviceWithHighestPrecedence