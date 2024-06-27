package kopring_prac.prac8_error.exception.resolver

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.web.servlet.HandlerExceptionResolver
import org.springframework.web.servlet.ModelAndView
import kotlin.Exception

private val logger = KotlinLogging.logger {  }

class MyHandlerException : HandlerExceptionResolver {
    override fun resolveException(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any?,
        ex: Exception
    ): ModelAndView? {

        try {
            if (ex is IllegalArgumentException){
                logger.info { "IllegalArgumentException resolver to 400" }
                response.sendError(HttpStatus.BAD_REQUEST.value(), "잘못된 사용자 입니다.")
                return ModelAndView()
            }

        }catch (e: Exception){
            logger.error { "resolver ex $e" }
        }

        return null
    }
}