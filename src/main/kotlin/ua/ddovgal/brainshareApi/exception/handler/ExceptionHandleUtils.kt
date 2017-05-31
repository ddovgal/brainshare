package ua.ddovgal.brainshareApi.exception.handler

import javax.servlet.http.HttpServletRequest

internal fun getFullURL(request: HttpServletRequest): String {
    val requestURL = request.requestURL
    val queryString: String? = request.queryString
    return queryString?.let { requestURL.append('?').append(it).toString() } ?: requestURL.toString()
}