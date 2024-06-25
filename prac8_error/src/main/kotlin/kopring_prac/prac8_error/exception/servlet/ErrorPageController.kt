package kopring_prac.prac8_error.exception.servlet

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

private val logger = KotlinLogging.logger {  }

@Controller
@RequestMapping("/error-page")
class ErrorPageController {

    @GetMapping("/404")
    fun errorPage404(request : HttpServletRequest, response : HttpServletResponse) : String{
        logger.info { "404 오류 발생!" }

        return "error-page/404"
    }

    @GetMapping("/500")
    fun errorPage500(request : HttpServletRequest, response : HttpServletResponse) : String{
        logger.info { "500 오류 발생!" }

        return "error-page/500"
    }
}