package prac.springmvc.basic.request

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpEntity
import org.springframework.stereotype.Controller
import org.springframework.util.StreamUtils
import org.springframework.web.bind.annotation.*
import prac.springmvc.basic.User
import java.nio.charset.StandardCharsets

private val logger = KotlinLogging.logger {  }

@RestController
class RequestBodyJsonController {

    private val objectMapper = jacksonObjectMapper()

    // HTTP 요청 Body 에 Json 값이 들어 있을 때, 제어
    // 1. request - inputStream 얻기.
    // 2. StreamUtils.copyToString(stream, 인코딩 방법)
    // 3. objectMapper 를 통해 Json 값을 역직렬화.
    @PostMapping("/request-body-json-v1")
    @ResponseBody
    fun requestBodyJsonV1(request: HttpServletRequest): String {
        val inputStream = request.inputStream
        val messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)

        val userData = objectMapper.readValue<User>(messageBody)

        logger.info { "username : ${userData.username} age : ${userData.age}" }

        return "requestBodyJsonV1 OK"
    }

    // Ver2. → @RequestBody 를 통해 바로 Json 값 가져오기.
    @PostMapping("/request-body-json-v2")
    @ResponseBody
    fun requestBodyJsonV2(@RequestBody messageBody : String): String {

        val userData = objectMapper.readValue<User>(messageBody)

        logger.info { "username : ${userData.username} age : ${userData.age}" }

        return "requestBodyJsonV2 OK"
    }

    // Ver3. → @RequestBody 로 바로 Json 값을 User 객체로 역직렬화
    @PostMapping("/request-body-json-v3")
    @ResponseBody
    fun requestBodyJsonV3(@RequestBody messageBody : User): String {

        logger.info { "username : ${messageBody.username} age : ${messageBody.age}" }

        return "requestBodyJsonV3 OK"
    }

    // Ver4. → @RequestBody 로 HttpEntity 를 통해 Json 값을 User 객체로 역직렬화
    @PostMapping("/request-body-json-v4")
    @ResponseBody
    fun requestBodyJsonV4(@RequestBody data : HttpEntity<User>): String {
        val messageBody = data.body
        logger.info { "username : ${messageBody?.username} age : ${messageBody?.age}" }

        return "requestBodyJsonV3 OK"
    }

    // Ver5. → @ResponseBody 로 역직렬화 된 User 객체를 다시 직렬화해서 응답할 수 있다.
    @PostMapping("/request-body-json-v5")
    @ResponseBody
    fun requestBodyJsonV5(@RequestBody messageBody : User): User {

        logger.info { "username : ${messageBody.username} age : ${messageBody.age}" }

        return messageBody
    }
}