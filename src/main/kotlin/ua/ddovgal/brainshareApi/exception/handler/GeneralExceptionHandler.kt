package ua.ddovgal.brainshareApi.exception.handler

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import ua.ddovgal.brainshareApi.model.ExceptionResponse
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class GeneralExceptionHandler {
    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun handleInvalidDataProvided(e: Exception, request: HttpServletRequest)
            = ExceptionResponse(e.message!!, getFullURL(request)) //TODO: message is exposing internal structure - fix it
}