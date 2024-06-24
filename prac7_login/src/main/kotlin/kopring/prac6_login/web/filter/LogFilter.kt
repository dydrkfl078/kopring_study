package kopring.prac6_login.web.filter

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.*
import jakarta.servlet.http.HttpServletRequest
import java.util.UUID

private val logger = KotlinLogging.logger { }

class LogFilter : Filter {

    override fun init(filterConfig: FilterConfig?) {
        logger.info { "log filter init" }
    }

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, filterChain: FilterChain?) {
        val httpRequest = request as HttpServletRequest
        val requestUrl = httpRequest.requestURL
        val uuid = "${UUID.randomUUID()}"

        try {
            logger.info { "REQUEST [ $uuid ] [ $requestUrl ]" }
            filterChain?.doFilter(request, response)

        } catch (e: Exception) {
            throw e
        } finally {
            logger.info { "RESPONSE [ $uuid ] [ $requestUrl ]" }
        }
    }

    override fun destroy() {
        logger.info { "log filter destroy" }
    }
}