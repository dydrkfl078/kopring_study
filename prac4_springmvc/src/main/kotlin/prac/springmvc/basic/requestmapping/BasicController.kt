package prac.springmvc.basic.requestmapping

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.websocket.server.PathParam
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger {}

@RestController
class BasicController {

    // 경로 변수 사용
    // @PathVariable("userId") userId : String
    // 위와 같이 경로 변수 이름과 파라미터 이름이 같을 경우 경로변수 명 생략 가능
    // → fun FUNCTION (@PathVariable userId : String) {}
    @GetMapping( "/path-variable/{user}")
    fun pathVariable(@PathVariable user : String): String {
        logger.info { "path variable = $user" }
        return "OK"
    }

    // 경로 변수 다중 매핑
    @GetMapping( "/path-variables/{user}/number/{num}")
    fun pathVariables(@PathVariable user : String, @PathVariable num : Int): String {
        logger.info { "path variables = $user, $num" }

        return "OK"
    }

    // 파라미터 매핑
    // params=["mode"]
    // params=["!mode"]
    // params=["mode=debug"]
    // params=["mode!=debug"]
    // params=["mode=debug","state=good"]

    @GetMapping( "/parameter" ,params = ["mode=service"])
    fun parameterMapping():String {
        logger.info { "parameterMapping" }
        return "Parameter mapping OK"
    }

    // 헤더 매핑
    // headers = ["string"]
    // headers = ["!string"]
    // headers = ["string=test"]
    // headers = ["string!=test"]
    // headers = ["string!=test", "state"]
    @GetMapping("/header", headers = ["string=test","state"])
    fun headerMapping():String {
        logger.info { "headerMapping" }
        return "Header mapping OK"
    }

    // Request header 의 Content - Type 기반의 Media Type 매핑
    // consumes = ["application/json"]
    // consumes = ["!application/json"]
    // consumes = ["application/json/*"]
    // consumes = ["*/*"]
    // MediaType.APPLICATION_JSON_VALUE
    @PostMapping("/content-type", consumes = ["application/json"])
    fun contentTypeMapping(): String {
        logger.info { "contentTypeMapping" }
        return "Content type mapping OK"
    }


    // Request header 의 accept ( 사용자가 받을 수 있는 타입 ) 기반의 매핑
    // produces = ["application/json"]
    // produces = ["!application/json"]
    // produces = ["application/*"]
    // produces = ["*/*"]
    @PostMapping("receiver-type", produces = ["application/json"])
    fun receiverTypeMapping(): String {
        logger.info { "receiverTypeMapping" }
        return "Receiver type mapping OK"
    }
}