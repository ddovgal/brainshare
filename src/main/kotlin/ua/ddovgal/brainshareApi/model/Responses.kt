package ua.ddovgal.brainshareApi.model

import org.springframework.http.HttpStatus
import java.time.Instant

open class BaseResponse(status: HttpStatus, val path: String) {
    val timestamp = Instant.now().toEpochMilli()
    val statusCode = status.value()
}

class ExceptionResponse(val message: String, path: String) : BaseResponse(HttpStatus.INTERNAL_SERVER_ERROR, path)
class InvalidDataProvidedResponse(val message: String, val reasons: Map<String, String>, path: String)
    : BaseResponse(HttpStatus.BAD_REQUEST, path)