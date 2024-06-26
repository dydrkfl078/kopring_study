package kopring_prac.prac8_error.exception.interceptor

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import java.lang.Exception
import java.util.*

private val logger = KotlinLogging.logger {  }

class LogInterceptor : HandlerInterceptor {

    companion object {
        const val LOG_ID = "logId"
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val uuid = "${UUID.randomUUID()}"
        val requestURI = request.requestURI

        request.setAttribute(LOG_ID, uuid)
        logger.info { "REQUEST [$uuid][${request.dispatcherType}][$requestURI][$handler]" }
        return true
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        logger.info { "postHandle $modelAndView" }
    }

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {
        val requestURI = request.requestURI
        val logId = request.getAttribute(LOG_ID) as String
        logger.info { "RESPONSE [$logId][${request.dispatcherType}][$requestURI]" }
        ex ?: logger.info { "AfterCompletion Error = $requestURI" }
    }
}