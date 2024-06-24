package kopring.prac6_login.web.interceptor

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import java.lang.Exception
import java.util.*

private val logger = KotlinLogging.logger {  }

class LogInterceptor : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val uuid = "${UUID.randomUUID()}"
        val requestURI = request.requestURI
        request.setAttribute("uuid", uuid)


        logger.info { "REQUEST = [$uuid][$requestURI][$handler]" }

        // return 값이 false 일 경우, 다음 핸들러로 이동 OR 마지막 핸들러일 경우 호출 X
        return true
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        logger.info { "postHandle mv = [$modelAndView]" }
    }

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {
        val requestURI = request.requestURI
        val uuid = request.getAttribute("uuid")
        logger.info { "RESPONSE [${uuid}][$requestURI][$handler][$ex]" }
    }
}