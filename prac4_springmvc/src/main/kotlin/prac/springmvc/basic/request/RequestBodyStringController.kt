package prac.springmvc.basic.request

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpEntity
import org.springframework.stereotype.Controller
import org.springframework.util.StreamUtils
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import java.io.InputStream
import java.io.Writer
import java.nio.charset.StandardCharsets

private val logger = KotlinLogging.logger {  }

@Controller
class RequestBodyStringController {

    @PutMapping("/request-body-string-v1")
    fun requestBodyString(request: HttpServletRequest, response : HttpServletResponse){
        val inputStream = request.inputStream
        val messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)

        logger.info { "message body : $messageBody" }

        response.writer.write("OK")
    }

    @PutMapping("/request-body-string-v2")
    fun requestBodyStringV2(inputStream: InputStream, writer : Writer){
        val messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)

        logger.info { "message body : $messageBody" }

        writer.write("OK")
    }

    @PutMapping("/request-body-string-v3")
    fun requestBodyStringV3(httpEntity: HttpEntity<String>):HttpEntity<String>{
        val messageBody = httpEntity.body

        logger.info { "message body : $messageBody" }

        return HttpEntity("OK")
    }

    @ResponseBody
    @PutMapping("/request-body-string-v4")
    fun requestBodyStringV4(@RequestBody messageBody : String): String{

        logger.info { "message body : $messageBody" }

        return "OK"
    }
}