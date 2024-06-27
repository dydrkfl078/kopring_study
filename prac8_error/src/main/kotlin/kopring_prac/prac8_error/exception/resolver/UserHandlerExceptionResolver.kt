package kopring_prac.prac8_error.exception.resolver

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kopring_prac.prac8_error.exception.exception.UserException
import org.springframework.http.MediaType
import org.springframework.ui.Model
import org.springframework.web.servlet.HandlerExceptionResolver
import org.springframework.web.servlet.ModelAndView
import kotlin.Exception
import kotlin.math.log

private val logger = KotlinLogging.logger {  }
private val objectMapper = jacksonObjectMapper()

class UserHandlerExceptionResolver : HandlerExceptionResolver {
    override fun resolveException(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any?,
        ex: Exception
    ): ModelAndView? {

        try {

            if (ex is UserException) {
                logger.info { "UserException to 400" }
                val acceptHeader = request.getHeader("accept")
                logger.info { "header = $acceptHeader" }
                response.status = HttpServletResponse.SC_BAD_REQUEST

                if (acceptHeader == "application/json"){
                    val errorResult : HashMap<String, Any> = HashMap()
                    errorResult["ex"] = ex::class
                    errorResult["message"] = ex.message!!

                    response.apply {
                        contentType = MediaType.APPLICATION_JSON_VALUE
                        characterEncoding = Charsets.UTF_8.name()
                        writer.write(objectMapper.writeValueAsString(errorResult))
                        return ModelAndView()
                    }
                } else {
                    logger.info { "???????" }
                    return ModelAndView("error/500")
                }

            }

        } catch (e: Exception) {
            logger.error { "resolver ex = $ex" }
        }

        return null
    }
}