package prac.springmvc.basic.request

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Controller
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

private val logger = KotlinLogging.logger {  }

@Controller
class RequestParamController {

    @RequestMapping("/request-param-v1")
    fun requestParamV1(request: HttpServletRequest, response: HttpServletResponse) {
        val username = request.getParameter("username")!!
        val age = request.getParameter("age")!!.toInt()
        logger.info { "username = $username age = $age" }

        response.writer.write("OK")
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    fun requestParamV2(@RequestParam username :String, @RequestParam age : Int): String {
        logger.info { "username = $username age = $age" }
        return "request param v2 OK"
    }

    // @RequestParam 으로 받는 값이 단순 타입이면 ( String, Int ) 생략 가능.
    // 직관적으로 Request Param 으로 값을 받아오는 걸 표현하는 것이 좋은 상황이 많다.
    @ResponseBody
    @RequestMapping("/request-param-v3")
    fun requestParamV3(username :String, age : Int): String {
        logger.info { "username = $username age = $age" }
        return "request param v3 OK"
    }

    // 필수 파라미터 설정하기.
    // @RequestParam( required = boolean ) required 값이 true 일 경우, 해당 파라미터가 필수 값이 된다.
    // required = true 인 파라미터가 존재하지 않을 경우 400 Bad Request 오류 발생.
    @ResponseBody
    @RequestMapping("/request-param-required")
    fun requiredParam(
        @RequestParam(required = true) username : String,
        @RequestParam(required = false) age : Int?
    ): String {
        logger.info { "username = $username age = $age " }
        return "OK"
    }

    // 파타미터의 디폴트 값 설정.
    // 파라미터 값이 없는 값들은 디폴트 값에 설정된 값으로 들어간다.
    // ★★★★★ 빈 문자열의 경우에도 디폴트 값이 들어간다 ★★★★★
    @ResponseBody
    @RequestMapping("/request-param-default")
    fun requireParamDefault(
        @RequestParam(defaultValue = "Guest") username : String,
        @RequestParam(defaultValue = "-1") age : Int
    ): String {
        logger.info { "username = $username age = $age" }
        return "OK"
    }

    // 도메인?username=hello&username=world! 와 같이 하나의 파라미터에 여러 값이 들어올 경우의 처리.
    // → MultiValueMap<String,String> 타입을 사용하여 핸들링.
    // < PARAMETER 이름(key) : String, values : LIST<T>> 형식으로 저장된다.
    @ResponseBody
    @RequestMapping("/request-param-map")
    fun requestParamMap(@RequestParam paramMap : MultiValueMap<String,String>): String{
        logger.info { "usernames = ${paramMap["username"]} age = ${paramMap["age"]}" }
        return "request param map OK"
    }
}