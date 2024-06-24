package kopring.prac6_login.web.filter

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kopring.prac6_login.web.session.SessionConst
import org.springframework.util.PatternMatchUtils

private val logger = KotlinLogging.logger {  }

class LoginCheckFilter: Filter {

    companion object {
        val whiteList = arrayOf("/","/members/add","/login","logout","/css/*","/home")
    }
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, filterChain: FilterChain?) {
        val httpRequest = request as HttpServletRequest
        val httpResponse = response as HttpServletResponse
        val requestURI = httpRequest.requestURI

        try {
            if (isLoginCheckPath(requestURI)){
                val session = httpRequest.getSession(false)
                if (session?.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                    logger.info { "미인증 사용자 요청 = $requestURI" }
                    httpResponse.sendRedirect("/login?redirectURL=$requestURI")
                }
            }

            filterChain?.doFilter(request,response)
        }catch (e: Exception){
            throw e
        }finally {
            logger.info { " LoginCheckFilter Complete = $requestURI" }
        }
    }

    private fun isLoginCheckPath(requestURI: String):Boolean {
        logger.info { "LoginCheck is true? ${PatternMatchUtils.simpleMatch(whiteList,requestURI)}" }
        return !PatternMatchUtils.simpleMatch(whiteList,requestURI)
    }
}