package kopring_prac.prac8_error.exception.filter

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.*
import jakarta.servlet.http.HttpServletRequest
import java.util.UUID

private val logger = KotlinLogging.logger {  }

class LogFilter : Filter{

    override fun init(filterConfig: FilterConfig?) {
        logger.info { "log filter init" }
    }

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, filterChain: FilterChain?) {
        val requestURI = (request as HttpServletRequest).requestURI
        val uuid = "${UUID.randomUUID()}"

        try {
            logger.info { "REQUEST [$uuid][${request.dispatcherType}][$requestURI]" }
            filterChain!!.doFilter(request,response)
        } catch (e: Exception) {
            logger.info { "ERROR = ${e.message}" }
            logger.info { "ERROR = ${e.message}" }
        } finally {
            logger.info { "log filter finish" }
        }
    }

    override fun destroy() {
        logger.info { "log filter destroy" }
    }
}