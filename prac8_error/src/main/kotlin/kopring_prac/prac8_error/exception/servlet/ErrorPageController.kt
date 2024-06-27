package kopring_prac.prac8_error.exception.servlet

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.RequestDispatcher
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

private val logger = KotlinLogging.logger {  }

@Controller
@RequestMapping("/error-page")
class ErrorPageController {

    @GetMapping("/400")
    fun errorPage404(request : HttpServletRequest, response : HttpServletResponse) : String{
        logger.info { "400 오류 발생!" }

        return "error-page/400"
    }

    @RequestMapping("/500")
    fun errorPage500(request : HttpServletRequest, response : HttpServletResponse) : String{
        logger.info { "500 오류 발생!" }

        return "error-page/500"
    }

    @RequestMapping(value = ["/500"], produces = [MediaType.APPLICATION_JSON_VALUE] )
    fun errorPage500Api(request : HttpServletRequest, response : HttpServletResponse) : ResponseEntity<HashMap<String,Any>>{
        logger.info { "API 500 오류 발생!" }

        val map = HashMap<String, Any>()
        val ex : Exception? = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION) as Exception?
        map["status"] = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)
        map["message"] = ex?.message!!

        logger.info { "result map = ${map.toList()}" }
        val statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE) as Int?
        return ResponseEntity(map, HttpStatus.valueOf(statusCode!!))
    }


}