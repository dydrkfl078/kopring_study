package kopring_prac.prac8_error.exception.servlet

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

private val logger = KotlinLogging.logger {  }

@Controller
class ServletExController {

    @GetMapping("/error-ex")
    fun errorEx(response: HttpServletResponse) {
        throw RuntimeException()
    }

    @GetMapping("/error-400")
    fun error400(response: HttpServletResponse) {
        response.sendError(400,"400 오류발생")
    }

    @GetMapping("/error-500")
    fun error500(response: HttpServletResponse) {
        response.sendError(500,"잘못된 사용자")
    }
}