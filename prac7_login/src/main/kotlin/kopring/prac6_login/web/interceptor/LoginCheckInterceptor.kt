package kopring.prac6_login.web.interceptor

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kopring.prac6_login.web.session.SessionConst
import org.springframework.web.servlet.HandlerInterceptor

private val logger = KotlinLogging.logger {  }

class LoginCheckInterceptor : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val requestURI = request.requestURI
        val session = request.session

        session?.getAttribute(SessionConst.LOGIN_MEMBER)?: run {
            logger.info { "미인증 사용자 요청 = $requestURI" }
            response.sendRedirect("/login?redirectURL=$requestURI")
            return false
        }

        return true
    }
}