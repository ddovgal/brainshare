package ua.ddovgal.brainshareApi.exception.handler

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import ua.ddovgal.brainshareApi.exception.ControllerAdviceWithHighestPrecedence
import ua.ddovgal.brainshareApi.exception.InvalidDataProvidedException
import ua.ddovgal.brainshareApi.model.InvalidDataProvidedResponse
import javax.servlet.http.HttpServletRequest

@ControllerAdviceWithHighestPrecedence
class InvalidDataProvidedExceptionHandler {
    @ExceptionHandler(InvalidDataProvidedException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleInvalidDataProvided(e: InvalidDataProvidedException, request: HttpServletRequest)
            = InvalidDataProvidedResponse(e.message!!, e.reasons, getFullURL(request))
}